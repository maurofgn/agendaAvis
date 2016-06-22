package it.mesis.avis.service.mapping;

import org.modelmapper.convention.MatchingStrategies;
import org.testng.annotations.Test;

import junit.framework.TestCase;

public class TestMapper extends TestCase {

	@Test
	public void testMap() throws Exception {
		OrderFactoryForTest orderFactoryForTest = new OrderFactoryForTest();
		Order order = orderFactoryForTest.newOrder();
		
		OrderServiceMapper mpa = new OrderServiceMapper();
		mpa.getModelMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);	//loose è "a maglie larghe"
		
//		ModelMapper modelMapper = new ModelMapper();
		OrderDto orderDto = mpa.mapOrderToOrderDto(order);
		
		chk(order, orderDto);
		
		Order order2 = new Order();
		mpa.mapOrderDtoToOrderEntity(orderDto, order2);
		chk(order2, orderDto);
	}
	
	private void chk(Order order, OrderDto orderDto) {
		assertEquals(orderDto.getBillingCity(), order.getBillingAddress().getCity());
		assertEquals(orderDto.getBillingStreet(), order.getBillingAddress().getStreet());
		
		assertEquals(orderDto.getCustomerFirstName(), order.getCustomer().getName().getFirstName());
		assertEquals(orderDto.getCustomerLastName(), order.getCustomer().getName().getLastName());
	}
	
}
