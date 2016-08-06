package edu.globant.hibernate.dao;

import javax.ejb.Stateless;

import edu.globant.hibernate.model.Bid;

@Stateless
public class BidDAOImpl extends GenericDAOImpl<Bid, Long>
    implements BidDAO {

    public BidDAOImpl() {
        super(Bid.class);
    }
}
