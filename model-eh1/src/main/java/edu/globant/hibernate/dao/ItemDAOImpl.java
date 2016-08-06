package edu.globant.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import edu.globant.hibernate.model.Bid;
import edu.globant.hibernate.model.Item;
import edu.globant.hibernate.model.ItemBidSummary;

@Stateless
public class ItemDAOImpl extends GenericDAOImpl<Item, Long>
    implements ItemDAO {

    public ItemDAOImpl() {
        super(Item.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Item> findAll(boolean withBids) {
    	StringBuilder query = new StringBuilder("SELECT DISTINCT i FROM Item i ");
        if (withBids)
            query.append("LEFT JOIN FETCH i.bids ");
        query.append("ORDER BY i.auctionEnd");
        return em.createQuery(query.toString()).getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Item> findByName(String name, boolean substring) {
        return   em.createNamedQuery(substring ? "getItemsByNameSubstring" : "getItemsByName")
        		.setParameter("itemName", substring ? ("%" + name + "%") : name)
        		.getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<ItemBidSummary> findItemBidSummaries() {
        return em.createNamedQuery("getItemBidSummaries").getResultList();
    }
}
