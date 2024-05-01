package com.example.myapp.buy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.myapp.buy.model.Buy;

public interface IBuyService {
	
	// 커피 주문
	public boolean insertBuy(int coffeeId, int custId);
	
	// 병훈 - 주문 리스트 페이지
	public List<Buy> getBuyList();

}
