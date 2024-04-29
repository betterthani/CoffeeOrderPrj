package com.example.myapp.buy.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Buy {
	
	private int buyId;
	private int coffeeId;
	private int customerId;
	private Date buyTime;
	private int buyCount;
	private String buyMenu;
	private int addShot;
	
	/*ID
	COFFEE_ID
	CUSTOMER_ID
	BUY_TIME
	BUY_COUNT
	BUY_MENU
	ADD_SHOT*/
}
