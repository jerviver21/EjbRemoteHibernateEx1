package edu.globant.hibernate.dao;

import java.util.List;

import edu.globant.hibernate.model.Item;
import edu.globant.hibernate.model.ItemBidSummary;

public interface ItemDAO extends GenericDAO<Item, Long> {

    List<Item> findAll(boolean withBids);

    List<Item> findByName(String name, boolean fuzzy);

    List<ItemBidSummary> findItemBidSummaries();

}
