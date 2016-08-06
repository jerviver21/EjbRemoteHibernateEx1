@GenericGenerators({
	@GenericGenerator(
			name = Constants.ID_GENERATOR,
			strategy = "enhanced-sequence",
			parameters = {
					@Parameter(
							name = "sequence_name",
							value = Constants.ID_GENERATOR_SEQUENCE_NAME
					)
			}
	)
})

@org.hibernate.annotations.NamedQueries({
	@org.hibernate.annotations.NamedQuery(
			name = "getItemsByNameSubstring",
			query = "select i from Item i where lower(i.name) like lower(:itemName)"
	),
	@org.hibernate.annotations.NamedQuery(
			name = "getItemsByName",
			query = "SELECT i FROM Item i WHERE i.name= :itemName"
	),
	@org.hibernate.annotations.NamedQuery(
			name = "getItemBidSummaries",
			query =   "select new org.jpwh.model.ItemBidSummary( "
					+ "	i.id, i.name, i.auctionEnd, max(b.amount)) "
					+ "from Bid b "
					+ "right outer join b.item i "
					+ "group by i.id, i.name, i.auctionEnd "
					+ "order by i.auctionEnd asc"
	)
})

package edu.globant.hibernate.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.Parameter;
import edu.globant.hibernate.common.*;

