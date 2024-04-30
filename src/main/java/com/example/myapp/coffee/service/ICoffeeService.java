package com.example.myapp.coffee.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.myapp.coffee.model.Coffee;

public interface ICoffeeService {
	
	public List<Coffee> getCoffeeList();
	public boolean updateCoffeeInfo(Coffee coffee, MultipartFile file);
	public Coffee getCoffeeById(int coffeeId);
	public boolean deleteFile(int coffeeId);
	
	//민서 - 커피 상세 정보
	public Coffee getCoffeeInfoDetail(int coffeeId);
	
	// 병훈 - 커피 정보 입력
    public boolean insertNewCoffeeInfo(Coffee coffee, MultipartFile file);
    // 병훈 - 커피 정보 삭제
    public boolean deleteCoffeeInfo(int coffeeId);

	
}
