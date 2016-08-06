package edu.globant.hibernate.services;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import edu.globant.hibernate.dao.BidDAO;
import edu.globant.hibernate.dao.ItemDAO;
import edu.globant.hibernate.model.Bid;
import edu.globant.hibernate.model.InvalidBidException;
import edu.globant.hibernate.model.Item;

@javax.ejb.Stateless
@javax.ejb.Local(AuctionService.class)
@javax.ejb.Remote(RemoteAuctionService.class)
public class AuctionServiceImpl implements AuctionService {

    @Inject
    protected ItemDAO itemDAO;

    @Inject
    protected BidDAO bidDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED) // Default
    public List<Item> getItems(boolean withBids) {
        return itemDAO.findAll(withBids);
    }

    @Override
    public Item storeItem(Item item) {
        return itemDAO.makePersistent(item);
    }

    @Override
    public Item placeBid(Bid bid) throws InvalidBidException {
        bid = bidDAO.makePersistent(bid);

        // Check that business rules are met
        if (!bid.getItem().isValidBid(bid))
            throw new InvalidBidException("Bid amount too low!");

        itemDAO.checkVersion(bid.getItem(), true);

        return bid.getItem();
    }
}