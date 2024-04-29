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
public class CoffeeService implements ICoffeeService{
	
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
	public boolean updateCoffeeInfo(Coffee coffee, MultipartFile file) {
		logger.info(file.getOriginalFilename());
		
		// 기존 파일 삭제
		deleteFile(coffee.getCoffeeId());
		
		// 파일 입력 
		logger.info(">>>파일 이름 : " + file.getOriginalFilename());
		try {
			if (file != null && !file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf("."));
				UUID uuid = UUID.randomUUID();
				String uuidFileName = uuid + fileExt;

				File saveFilePath = new File("C:\\dev\\upload", uuidFileName); // 해당 경로에 따른 파일 객체 생성됨.
				file.transferTo(saveFilePath); // 파일에 저장
				
				Coffee newCoffee = new Coffee();
				newCoffee.setCoffeeName(coffee.getCoffeeName());
				newCoffee.setKaclInfo(coffee.getKaclInfo());
				newCoffee.setCoffeeImage(uuidFileName);
				newCoffee.setAmount(coffee.getAmount());
				newCoffee.setCategory(coffee.getCategory());
				newCoffee.setIceHot(coffee.getIceHot());
				coffeeRepository.updateCoffeeInfo(newCoffee);
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	// 커피 정보 조회
	@Override
	public Coffee getCoffeeById(int coffeeId) {
		return coffeeRepository.getCoffeeById(coffeeId);
	}
	
	// 기존 커피 이미지 삭제
	@Override
	@Transactional
	public boolean deleteFile(int coffeeId) {
		String uploadDir = "C:\\dev\\coffeeUpload";
		String uuidFileName = getCoffeeById(coffeeId).getCoffeeImage();
		logger.info(">>>CoffeeService_deleteFile, 기존 파일명 이름 : " + uuidFileName);
		
		if(uuidFileName != null) {
			// 기존 정보가 있을 경우 삭제
			File deletedFile = new File(uploadDir, uuidFileName);
			boolean isDeleted = deletedFile.delete();
			
			if(isDeleted) {
				// 파일 삭제 완료
				coffeeRepository.deleteFile(coffeeId);
				logger.info(">>>파일 삭제 완료");
				return true;
				
			} else {
				logger.info(">>>파일 삭제 실패");
				throw new RuntimeException("file not deleted");
			}
		} else {
			logger.info(">>>기존의 파일이 없습니다.");
			return false;
		}
	}
	
	
	
}
