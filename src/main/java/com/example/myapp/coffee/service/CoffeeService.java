package com.example.myapp.coffee.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.myapp.coffee.dao.ICoffeeRepository;
import com.example.myapp.coffee.model.Coffee;

@Service
public class CoffeeService implements ICoffeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ICoffeeRepository coffeeRepository;

	// 커피 리스트 조회
	@Override
	public List<Coffee> getCoffeeList() {
		return coffeeRepository.getCoffeeList();
	}

	// 커피 정보 수정
	@Override
	@Transactional
	public boolean updateCoffeeInfo(Coffee coffee) {
		return coffeeRepository.updateCoffeeInfo(coffee);
	}
	
	// 커피정보 파일 삭제
	@Override
	@Transactional
	public void deleteFile(int coffeeId) {
		// 해당 커피 정보 찾기
		Coffee coffeeInfo = coffeeRepository.getCoffeeById(coffeeId);
		
		// 기존 이미지 파일 경로 갖고 오기
		String uuidFileName = coffeeInfo.getCoffeeImage(); 
		logger.info(">>>> coffeeService 기존 파일 경로 : " + uuidFileName);
		File file = new File(uuidFileName);
		boolean isDeleted = file.delete();
	}

	// 커피 정보 조회
	@Override
	public Coffee getCoffeeById(int coffeeId) {
		return coffeeRepository.getCoffeeById(coffeeId);
	}
	
	// 민서_커피 정보 
	@Override
	public Coffee getCoffeeInfoDetail(int coffeeId) {
		return coffeeRepository.getCoffeeInfoDetail(coffeeId);
	}

	// 병훈 - 커피 정보 입력
    @Override
    @Transactional
    public boolean insertNewCoffeeInfo(Coffee coffee) {
        return coffeeRepository.insertNewCoffeeInfo(coffee);
    }

    // 병훈- 커피 정보 삭제
    @Override
    @Transactional
    public void deleteCoffeeInfo(int coffeeId) {
        coffeeRepository.deleteCoffeeInfo(coffeeId);
    }


	

	
	
	

}
