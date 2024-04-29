package com.example.myapp.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.customer.dao.ICustomerRepository;
import com.example.myapp.customer.model.Customer;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;
	
	// 사원번호, 비밀번호로 customer 정보 조회하기
	@Override
	public Customer getCustomer(String employeeNumber, String password) {
		return customerRepository.getCustomer(employeeNumber, password);
	}

	
}
