package com.example.myapp.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.customer.model.Customer;
import com.example.myapp.customer.service.ICustomerService;

@Controller
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ICustomerService customerService;
	
	/**
	 * 예외처리
	 * @param request
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler({RuntimeException.class})
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		logger.error("DB Error: URL-{}, EX-{} ", request.getRequestURL(), ex);
		model.addAttribute("exception", ex);
		model.addAttribute("url", request.getRequestURL());
		return "error/runtime";
	}
	
	/**
	 * 로그인 화면 
	 * @return
	 */
	@GetMapping("/coffee/login")
	public String getLogin() {
		return "coffee/login";
	}
	
	/**
	 * 로그인
	 * @param employeeNumber
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/coffee/login")
	public String getLogin(String employeeNumber
							, String password
							, HttpSession session
							, RedirectAttributes redirectAttr) {
		if(employeeNumber == null || employeeNumber == "") {
			// 사원번호(로그인id)가 없을 경우
			return "coffee/login";
		} else {
			// 사원번호가 있을 경우
			// 비밀번호 조회
			Customer custInfo = customerService.getCustomer(employeeNumber, password);
			if(custInfo != null) {
				// 조회한 비밀번호가 있을 경우
				if(custInfo.equals(password)) {
					// 비밀번호 맞을 경우 
					// 세션 정보에 저장
					session.setAttribute("custId", custInfo.getCustId()); //pk
					session.setAttribute("role", custInfo.getRole()); // 관리자여부
					session.setAttribute("employeeNumber", employeeNumber);//사원번호(로그인id)
					return "coffee/mainPage";
				} else {
					// 비밀번호 맞지 않을 경우
					redirectAttr.addFlashAttribute("message", "비밀번호가 맞지 않습니다.");
					return "redirect:/coffee/login";
				}
			} else {
				// 조회한 비밀번호가 없을 경우
				redirectAttr.addFlashAttribute("message", "입력하신 비밀번호가 없습니다.");
				return "redirect:/coffee/login";
			}
		}
	} //-> 로그인 메서드 종료
	
	
	
}
