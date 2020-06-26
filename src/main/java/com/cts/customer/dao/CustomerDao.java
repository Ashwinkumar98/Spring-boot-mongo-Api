package com.cts.customer.dao;
import java.util.List;

import com.cts.customer.modal.Customer;

public interface CustomerDao {
	Customer save(Customer customer);
	List<Customer> findAll();
	Customer findById(int id);
	void deleteById(int id);
	Customer update(Customer customer);
}
