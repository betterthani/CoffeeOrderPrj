package com.example.myapp.buy.service;

import java.util.List;

import com.example.myapp.buy.model.Buy;

public interface IBuyService {
	
	// 커피 주문
	public boolean insertBuy(Buy buy);
	
	// 병훈 - 주문 리스트 페이지
	public List<Buy> getBuyList();

}
