package com.example.myapp.customer.service;

import com.example.myapp.customer.model.Customer;

public interface ICustomerService {

	public Customer getCustomer(String employeeNumber, String password);
	boolean signUpCustomer(Customer customer);
	public int getCustomerNumber(String employeeNumber);
	
}
