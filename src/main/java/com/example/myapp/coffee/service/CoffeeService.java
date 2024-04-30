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
		logger.info(">>>> 기존 파일 경로 : " + uuidFileName);
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
	public boolean insertNewCoffeeInfo(Coffee coffee, MultipartFile file) {
		try {
			if (file != null && !file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf("."));
				UUID uuid = UUID.randomUUID();
				String uuidFileName = uuid + fileExt;

				// 파일 저장 경로 설정
				String uploadDir = "C://dev//coffeeUpload";
				File saveFilePath = new File(uploadDir, uuidFileName);
				file.transferTo(saveFilePath);

				// 커피 정보 저장
				Coffee newCoffee = new Coffee();
				newCoffee.setCoffeeName(coffee.getCoffeeName());
				newCoffee.setKaclInfo(coffee.getKaclInfo());
				newCoffee.setCoffeeImage(uploadDir + "//" + uuidFileName);
				newCoffee.setAmount(coffee.getAmount());
				newCoffee.setCategory(coffee.getCategory());
				newCoffee.setIceHot(coffee.getIceHot());
				coffeeRepository.insertCoffeeInfo(newCoffee);

				return true;
			} else {
				logger.info("업로드된 파일이 없습니다.");
			}
		} catch (Exception e) {
			logger.info("잘못된 정보 입력입니다.: " + e.getMessage());
		}
		return false;
	}

	// 병훈 - 커피 정보 삭제
	@Override
	@Transactional
	public boolean deleteCoffeeInfo(int coffeeId) {
		try {
			
            // 주어진 ID에 해당하는 커피가 있는지 확인
            Coffee deleteCoffeeInfo = coffeeRepository.getCoffeeById(coffeeId);
            if (deleteCoffeeInfo == null) {
                logger.error(coffeeId + " 번 커피는 존재하지 않습니다.");
                return false;
            }

            // 폴더에 적재되어있는 파일 삭제 안 할 건지?
            // 데이터베이스에서 커피 정보 삭제
            coffeeRepository.deleteCoffeeId(coffeeId);
            logger.info(coffeeId + " 번 커피가 성공적으로 삭제되었습니다.");
            return true;
            
        } catch (Exception e) {
        	logger.info("에러메세지 : " + e.getMessage());
        } finally {
            logger.info("커피 정보 삭제 실패");
        }
        return false;
	}

	

}
