package com.example.myapp.buy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.buy.dao.IBuyRepository;
import com.example.myapp.buy.model.Buy;

@Service
public class BuyService implements IBuyService{
	
	@Autowired
	IBuyRepository buyRepository;

	@Override
	@Transactional
	public boolean insertBuy(int coffeeId, int custId) {
		return buyRepository.insertBuy(coffeeId, custId);
	}
	
	// 병훈 - 주문 리스트
	@Override
	public List<Buy> getBuyList(int custId) {
		return buyRepository.getBuyList(custId);
	}
	

}
