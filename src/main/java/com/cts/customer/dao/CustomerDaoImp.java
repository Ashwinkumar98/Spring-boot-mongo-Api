package com.cts.customer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.cts.customer.modal.Customer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerDaoImp implements CustomerDao{
	
	@Value("${document.name}")
	private String documentName;
	
	private final MongoTemplate mongoTemplate;
	
	@Override
	public Customer save(Customer customer) {
		mongoTemplate.save(customer);
		return customer;
	}

	@Override
	public List<Customer> findAll() {
		return mongoTemplate.findAll(Customer.class,documentName);
	}

	@Override
	public Customer findById(int id) {
		return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Customer.class);
	}

	@Override
	public void deleteById(int id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)),Customer.class);
	}

	@Override
	public Customer update(Customer customer) {
		Update update = new Update();
		update.set("id",customer.getId());
		update.set("firstName", customer.getFirstName());
		update.set("lastName", customer.getLastName());
		update.set("emailId", customer.getEmailId());
		mongoTemplate.updateFirst(
				Query.query(Criteria.where("id").is(customer.getId())),
				update, 
				Customer.class);
		return customer;
	}
	
}
