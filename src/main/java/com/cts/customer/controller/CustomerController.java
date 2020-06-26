package com.cts.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer.exception.ResourceAlreadyFoundException;
import com.cts.customer.exception.ResourceNotFoundException;
import com.cts.customer.modal.Customer;
import com.cts.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(
				service.addCustomer(customer).
				orElseThrow(()-> new ResourceAlreadyFoundException("id already exists")), HttpStatus.OK);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		return new ResponseEntity<List<Customer>>(service.getAllCustomer(), HttpStatus.OK);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id",required = true) Integer id){
		return new ResponseEntity<Customer>(
				service.getCustomerById(id).
				orElseThrow(()-> new ResourceNotFoundException("customer id " +id+" not found")), HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name="id",required = true) Integer id){
		if(service.getDeleteById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("customer id" +id+" not found");
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(
				service.updateCustomer(customer).
				orElseThrow(()-> new ResourceNotFoundException("id not exists")),HttpStatus.OK);
	}
	
}
