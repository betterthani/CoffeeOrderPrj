package com.example.myapp.buy.dao;

import java.util.List;

import com.example.myapp.buy.model.Buy;

public interface IBuyRepository {
	
	// 커피 주문
	public boolean insertBuy(Buy buy);
	
	//병훈 - 주문 리스트
	public List<Buy> getBuyList();

}
