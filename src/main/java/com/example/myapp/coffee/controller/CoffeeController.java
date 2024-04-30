package com.example.myapp.coffee.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.coffee.dao.ICoffeeRepository;
import com.example.myapp.coffee.model.Coffee;
import com.example.myapp.coffee.service.ICoffeeService;

@Controller
public class CoffeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ICoffeeService coffeeService;
	
	@Autowired
	ICoffeeRepository repo;

	/**
	 * 커피 리스트 조회 화면
	 * 
	 * @return
	 */
	@GetMapping("/coffee/list")
	public String getCoffeeList(Model model) {
		List<Coffee> coffeeList = coffeeService.getCoffeeList();
		model.addAttribute("coffeeList", coffeeList);
		return "coffee/list";
	}

	/**
	 * 커피 정보 수정 화면
	 */
	@GetMapping("/coffee/list/b")
	public String updateCoffeeInfo(Model model, @PathVariable int coffeeId) {
		// 커피 정보 조회한거 가지고오기
		Coffee coffee = coffeeService.getCoffeeById(coffeeId);
		model.addAttribute("Coffee", coffee);
		return "coffee/list";
	}

	/**
	 * 커피 정보 수정
	 * 
	 * @param coffee
	 * @param redirectAttr
	 * @return
	 */
	@PutMapping("/coffee/list/b")
	public String updateCoffeeInfo(@RequestParam Coffee coffee
									, @RequestParam MultipartFile file
									, HttpSession session,
									RedirectAttributes redirectAttr) {
		// 세션 정보에서 role이 관리자가 맞을 경우에만 update
		String role = (String) session.getAttribute("role");
		if (role.equals("ROLE_ADMIN")) {
			// 관리자일 경우 update가능
			// 기존 파일 삭제
			coffeeService.deleteFile(coffee.getCoffeeId());
			
			try {
				if(file != null && !file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					String fileExt = fileName.substring(fileName.lastIndexOf("."));
					UUID uuid = UUID.randomUUID();
					String uuidFileName = uuid + fileExt;

					logger.info(">>>>업데이트할 파일 본명: " + file.getOriginalFilename());
					String uploadDir = session.getServletContext().getRealPath("/upload");
					logger.info(">>>>저장 될 경로 : " + uploadDir);

					File saveFilePath = new File(uploadDir, uuidFileName); // 해당 경로에 따른 파일 객체 생성됨.
					file.transferTo(saveFilePath); // 파일에 저장

					Coffee newCoffee = new Coffee();
					newCoffee.setCoffeeName(coffee.getCoffeeName());
					newCoffee.setKaclInfo(coffee.getKaclInfo());
					newCoffee.setCoffeeImage(uploadDir + uuidFileName);
					newCoffee.setAmount(coffee.getAmount());
					newCoffee.setCategory(coffee.getCategory());
					newCoffee.setIceHot(coffee.getIceHot());
					coffeeService.updateCoffeeInfo(newCoffee);
				}
			} catch (Exception e) {
				redirectAttr.addFlashAttribute("message", e.getMessage());
			}
		} else {
			// 관리자가 아닐경우 update불가
			redirectAttr.addFlashAttribute("message", "관리자만 게시물 수정이 가능합니다.");
		}
		return "redirect:/coffee/list";
	}
	
	/**
	 * 민서 - 커피 정보
	 * @param coffeeId
	 * @param model
	 * @return
	 */
	@GetMapping("/coffee/detail/{coffeeId}")
	public String getCoffeeInfoDetail(@PathVariable int coffeeId, Model model) {
		Coffee coffeeInfo = coffeeService.getCoffeeInfoDetail(coffeeId);
		model.addAttribute("coffeeInfo", coffeeInfo);
		return "/coffee/detail/{coffeeId}";
	}   
	
	/**
	 * 병훈 - 새로운 커피 정보 추가 화면
	 */
	@GetMapping("/coffee/list/a")
	public String insertNewCoffeeInfo(Model model) {
		model.addAttribute("coffee", new Coffee());
		return "coffee/insert"; // Assuming you have an insert page
	}

	/**
	 * 병훈 - 새로운 커피 정보 입력
	 *
	 * @param coffee
	 * @param file
	 * @param session
	 * @param redirectAttr
	 * @return
	 */
	@PostMapping("/coffee/list/a")
	public String saveNewCoffeeInfo(@ModelAttribute Coffee coffee,
									@RequestParam MultipartFile file,
									HttpSession session,
									RedirectAttributes redirectAttr) {
		// 세션 정보에서 role이 관리자인지 확인
		String role = (String) session.getAttribute("role");
		if (role != null && role.equals("ROLE_ADMIN")) {
			// 관리자인 경우에만 추가 가능
			try {
				boolean isCoffeeAdded = coffeeService.insertNewCoffeeInfo(coffee, file);
				if (isCoffeeAdded) {
					// 추가 성공
					redirectAttr.addFlashAttribute("message", "커피 정보 추가 되었습니다.");
				} else {
					// 추가 실패
					redirectAttr.addFlashAttribute("message", "커피 정보 추가에 실패했습니다.");
				}
			} catch (RuntimeException e) {
				redirectAttr.addFlashAttribute("message", e.getMessage());
			}
		} else {
			// 관리자가 아닌 경우 추가 불가
			redirectAttr.addFlashAttribute("message", "관리자만 커피 정보를 추가할 수 있습니다.");
		}
		return "redirect:/coffee/list";
	}

	/**
	 * 병훈 - 커피 정보 삭제
	 *
	 * @param coffeeId
	 * @param session
	 * @param redirectAttr
	 * @return
	 */
	@DeleteMapping("/coffee/list/{coffeeId}")
	public String deleteCoffeeInfo(@PathVariable int coffeeId,
									HttpSession session,
									RedirectAttributes redirectAttr) {
		// 세션 정보에서 role이 관리자인지 확인
		String role = (String) session.getAttribute("role");
		if (role != null && role.equals("ROLE_ADMIN")) {
			// 관리자인 경우에만 삭제 가능
			try {
				boolean isCoffeeDeleted = coffeeService.deleteCoffeeInfo(coffeeId);
				if (isCoffeeDeleted) {
					// 삭제 성공
					return "redirect:/coffee/list";
				} else {
					// 삭제 실패
					redirectAttr.addFlashAttribute("message", "커피 정보 삭제에 실패했습니다.");
				}
			} catch (RuntimeException e) {
				redirectAttr.addFlashAttribute("message", e.getMessage());
			}
		} else {
			// 관리자가 아닌 경우 삭제 불가
			redirectAttr.addFlashAttribute("message", "관리자만 커피 정보를 삭제할 수 있습니다.");
		}
		return "redirect:/coffee/list";
	}

	
	
}
