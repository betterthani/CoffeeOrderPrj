package com.example.myapp.buy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.buy.model.Buy;
import com.example.myapp.buy.service.IBuyService;

@Controller
public class BuyController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IBuyService buyService;

	/**
	 * 커피 주문 입력 화면
	 * @return
	 */
	@GetMapping("/coffee/buy/a")
	public String insertBuy() {
		return "coffee/buy/a";
	}
	
	/**
	 * 커피 주문
	 * @param buy
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/coffee/buy/a")
	public String insertBuy(Buy buy, RedirectAttributes redirectAttr) {
		try {
			boolean isBuyInsert = buyService.insertBuy(buy);
			if(isBuyInsert) {
				// insert가 성공했을 경우
				// insert 후 커피 주문 리스트 조회화면으로 이동
				return "coffee/buy/list";
			} else {
				// insert가 실패했을 경우
				redirectAttr.addFlashAttribute("message", "커피 주문에 실패했습니다.");
				return "coffee/buy/list";
			}
		} catch(RuntimeException e) {
			redirectAttr.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/coffee/buy/list";
	}
	
	/**
	 * 병훈 - 주문 리스트 페이지
	 * @param model
	 * @param session
	 * @return 주문 리스트 뷰
	 */
	@GetMapping("/coffee/orderList")
	public String getOrderList(Model model, HttpSession session) {
		/*// 사용자가 로그인했는지 확인
		if (session.getAttribute("custId") == null) {
			// 로그인되지 않았다면 로그인 페이지로 리다이렉트
			return "redirect:/coffee/login";
		}*/

		// 주문 목록을 서비스로부터 가져옴
		List<Buy> buyList = buyService.getBuyList();

		// 모델에 주문 목록 추가
		model.addAttribute("buyList", buyList);

		// 주문 리스트 페이지의 뷰 이름 반환
		return "coffee/orderList";
	}

	

}
