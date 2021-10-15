package kr.itaz.lms.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
/**
 * @packageName		: kr.itaz.lms.common.interceptor
 * @fileName		: AuthInterceptor.java
 * @author			: jsoh
 * @date			: 2021.09.06
 * @description		:
 * =======================================================
 * DATE				AUTHOR			NODE
 * =======================================================
 * 2021.10.13		bnso			최초 생성
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: AuthInterceptor START ::::::::::::::::::::::::::::::::::::::::::::::::::::");

		@SuppressWarnings("unchecked")
		Map<String, Object> authMap = (Map<String, Object>) request.getSession().getAttribute("auth");

		if(authMap == null) {
			logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: 인증 정보가 존재하지 않습니다. ::::::::::::::::::::::::::::::::::::::::::::::::::::");
			response.sendRedirect("/auth/login");
			return false;
		}
		 

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {

		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: AuthInterceptor END ::::::::::::::::::::::::::::::::::::::::::::::::::::");
 
	}
}