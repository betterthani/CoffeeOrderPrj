package com.example.myapp.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	// 병훈 - 회원가입
	@Override
	@Transactional
	public boolean signUpCustomer(Customer customer) {
		// 회원가입에 필요한 비즈니스 로직 구현
        // 이 예시에서는 DAO를 사용하여 회원 정보를 저장합니다.
        // 필요에 따라서 비밀번호 암호화, 유효성 검사 등의 로직을 추가할 수 있습니다.
        return customerRepository.signUpCustomer(customer);

	}
	
	// 회원가입시 사원번호 존재하는지 조회
	@Override
	public int getCustomerNumber(String employeeNumber) {
		return customerRepository.getCustomerNumber(employeeNumber);
	}

	
}
