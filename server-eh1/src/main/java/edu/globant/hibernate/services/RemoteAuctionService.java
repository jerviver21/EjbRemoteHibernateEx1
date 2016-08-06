package edu.globant.hibernate.services;

import java.util.List;

import edu.globant.hibernate.model.Bid;
import edu.globant.hibernate.model.InvalidBidException;
import edu.globant.hibernate.model.Item;

public interface RemoteAuctionService {

    List<Item> getItems(boolean withBids);

    Item storeItem(Item item);

    Item placeBid(Bid bid) throws InvalidBidException;
}
