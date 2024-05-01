package com.example.myapp.buy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.myapp.buy.model.Buy;

public interface IBuyRepository {
	
	// 커피 주문
	public boolean insertBuy(@Param("coffeeId") int coffeeId, @Param("custId") int custId);
	
	//병훈 - 주문 리스트
	public List<Buy> getBuyList();

}
