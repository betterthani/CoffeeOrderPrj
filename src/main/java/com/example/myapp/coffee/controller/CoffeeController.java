package com.example.myapp.coffee.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/coffee/detail/{coffeeId}")
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
			boolean isUpdateCoffee = coffeeService.updateCoffeeInfo(coffee, file);
			try {
				if (isUpdateCoffee) {
					// update 성공
					return "coffee/list";
				} else {
					// update 실패
					redirectAttr.addFlashAttribute("message", "업데이트에 실패했습니다.");
				}
			} catch (RuntimeException e) {
				redirectAttr.addFlashAttribute("message", e.getMessage());
			}
		} else {
			// 관리자가 아닐경우 update불가
			redirectAttr.addFlashAttribute("message", "관리자만 게시물 수정이 가능합니다.");
		}
		return "redirect:/coffee/list";
	}


}
