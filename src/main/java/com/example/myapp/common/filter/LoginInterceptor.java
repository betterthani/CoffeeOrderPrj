package com.example.myapp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		try {
			// 세션에 정보가 있는지 확인 한 후, 정보가 없으면 로그인 페이지로 Redirect
			String role = (String) session.getAttribute("role");
			if(role == null || role.equals("")) {
				response.sendRedirect(request.getContextPath() + "/coffee"); // request.getContextPath() = myapp경로(기본 경로)
			} else {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace(); // 예외발생 경로 추적하여 보여줌.
			logger.info("인터셉터 에러 : "+ e.getMessage());
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
