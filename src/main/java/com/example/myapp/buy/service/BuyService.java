package com.example.myapp.buy.service;

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
	@Transactional("transactionManager")
	public boolean insertBuy(Buy buy) {
		return buyRepository.insertBuy(buy);
	}
	

}
