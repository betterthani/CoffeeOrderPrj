package com.example.myapp.buy.dao;

import com.example.myapp.buy.model.Buy;

public interface IBuyRepository {
	
	// 커피 주문
	public boolean insertBuy(Buy buy);

}
