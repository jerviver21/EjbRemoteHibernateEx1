package edu.globant.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import edu.globant.hibernate.common.Constants;

@Entity
@org.hibernate.annotations.Immutable
public class Bid implements Serializable, Comparable<Bid> {
	
	@Id
	@GeneratedValue(generator= Constants.ID_GENERATOR)
	protected Long id;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	protected Item item;
	
	@NotNull
	@Column(updatable= false)
	protected BigDecimal amount;
	
	@NotNull
	@Column(updatable = false)
	protected Date createdOn = new Date();
	
	
	public Bid() {
    }

    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public int compareTo(Bid o) {
		if(this.getAmount() == null || o.getAmount() == null) return 0;
		return this.getAmount().compareTo(o.getAmount());
	}
	
}
