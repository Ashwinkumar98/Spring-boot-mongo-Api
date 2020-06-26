package com.cts.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customer.dao.CustomerDao;
import com.cts.customer.modal.Customer;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Optional<Customer> addCustomer(Customer customer) {
		if(getCustomerById(customer.getId()).isPresent())
			return Optional.empty();
		return Optional.of(customerDao.save(customer));
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int id) {
		Customer customer = customerDao.findById(id);
		if(customer!=null)
			return Optional.of(customer);
		return Optional.empty();
	}

	@Override
	public boolean getDeleteById(int id) {
		if(getCustomerById(id).isPresent()) {
			customerDao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Optional<Customer> updateCustomer(Customer customer) {
		if(getCustomerById(customer.getId()).isPresent()) {
			return Optional.of(customerDao.update(customer));
		}
		return Optional.empty();
	}
	
}
