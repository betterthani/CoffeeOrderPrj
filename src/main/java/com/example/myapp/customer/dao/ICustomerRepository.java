package com.example.myapp.customer.dao;

import org.apache.ibatis.annotations.Param;

import com.example.myapp.customer.model.Customer;

public interface ICustomerRepository {
	
	// 사원번호, 비밀번호로 customer 정보 조회하기
	public Customer getCustomer(@Param("employeeNumber") String employeeNumber, @Param("password") String password);
	

}
