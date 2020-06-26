package com.cts.customer.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Customer")
@Data
@NoArgsConstructor
public class Customer {
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
}
