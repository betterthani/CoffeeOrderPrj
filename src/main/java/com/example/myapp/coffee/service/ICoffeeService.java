package com.example.myapp.coffee.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.myapp.coffee.model.Coffee;

public interface ICoffeeService {
	
	public List<Coffee> getCoffeeList();
	public boolean updateCoffeeInfo(Coffee coffee, MultipartFile file);
	public Coffee getCoffeeById(int coffeeId);
	public boolean deleteFile(int coffeeId);
	
}
