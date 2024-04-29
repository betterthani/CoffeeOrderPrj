package com.example.myapp.coffee.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coffee {
	
	private int coffeeId;
	private String coffeeName;
	private String kaclInfo;
	private String coffeeImage;
	private int amount;
	private String category;
	private String iceHot;
	
	/*ID
	COFFEE_NAME
	KCAL_INFO
	COFFEE_IMAGE
	AMOUNT
	CATEGORY
	ICE_HOT*/
}
