package org.groovy.cookbook

import static org.junit.Assert.*
import org.junit.*

class TestCustomerService {
	@Test
	void testGetCustomer() {
		boolean daoCalled = false
		CustomerDao.metaClass.getCustomerById = { Long id ->
			daoCalled = true
			new Customer(name: 'Yoda')
		}
		def cs = new CustomerService()
		def cDao = new CustomerDao()
		cs.setCustomerDao(cDao)
		def customer = cs.getCustomer(100L)
		assertTrue(daoCalled)
		assertEquals(customer.name, 'Yoda')
	}
}