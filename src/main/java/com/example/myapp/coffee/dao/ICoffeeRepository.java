package com.example.myapp.coffee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.example.myapp.coffee.model.Coffee;

public interface ICoffeeRepository {
	// 커피 리스트 조회
	public List<Coffee> getCoffeeList();
	
	// 커피 정보 수정
	public boolean updateCoffeeInfo(Coffee coffee);
	
	// 커피 정보 조회
	public Coffee getCoffeeById(int coffeeId);
	
	// 커피 이미지 삭제
	boolean deleteFile(int coffeeId);
}
