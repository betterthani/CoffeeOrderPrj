package com.example.myapp.coffee.dao;

import java.util.List;

import com.example.myapp.coffee.model.Coffee;

public interface ICoffeeRepository {
	// 커피 리스트 조회
	public List<Coffee> getCoffeeList();

	// 커피 정보 수정
	public boolean updateCoffeeInfo(Coffee coffee);

	// 커피 정보 조회
	public Coffee getCoffeeById(int coffeeId);

	// 민서_커피 상세 정보
	public Coffee getCoffeeInfoDetail(int coffeeId);
	
	// 병훈 - 커피 정보 입력
	public boolean insertNewCoffeeInfo(Coffee coffee);

	// 병훈 - 커피 정보 삭제
	public void deleteCoffeeInfo(int coffeeId);


}
