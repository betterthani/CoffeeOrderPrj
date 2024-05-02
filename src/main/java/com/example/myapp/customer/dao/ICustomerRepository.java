package com.example.myapp.customer.dao;

import org.apache.ibatis.annotations.Param;

import com.example.myapp.customer.model.Customer;

public interface ICustomerRepository {
	
	// 사원번호, 비밀번호로 customer 정보 조회하기
	public Customer getCustomer(@Param("employeeNumber") String employeeNumber, @Param("password") String password);
	
	// 병훈 - 회원가입
	public boolean signUpCustomer(Customer customer);

	// 회원가입시 사원번호 존재하는지 조회
	public int getCustomerNumber(String employeeNumber); 
}
