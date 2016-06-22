package it.mesis.avis.service.mapping;

import it.mesis.avis.service.mapping.Order;
import it.mesis.avis.test.MockValues;

public class OrderFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Order newOrder() {

		Customer customer = new Customer(new Name(mockValues.nextString(5), mockValues.nextString(8)));
		Address address = new Address(mockValues.nextString(9), mockValues.nextString(5));
		Order order = new Order(customer, address);
			
		return order;
	}
	
}
