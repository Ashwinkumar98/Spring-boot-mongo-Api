package com.cts.customer.service;

import java.util.List;
import java.util.Optional;

import com.cts.customer.modal.Customer;

public interface CustomerService {
	Optional<Customer> addCustomer(Customer customer);
	List<Customer> getAllCustomer();
	Optional<Customer> getCustomerById(int id);
	boolean getDeleteById(int id);
	Optional<Customer> updateCustomer(Customer customer);
}
