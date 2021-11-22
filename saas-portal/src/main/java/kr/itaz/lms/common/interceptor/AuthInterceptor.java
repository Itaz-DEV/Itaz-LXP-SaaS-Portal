package kr.itaz.lms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.itaz.lms.portal.vo.PortalVO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @packageName		: kr.itaz.lms.common.interceptor
 * @fileName		: AuthInterceptor.java
 * @author			: jsoh
 * @date			: 2021.09.06
 * @description		:
 */
@Component
@Slf4j
@NoArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean result = true;
		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: AuthInterceptor START ::::::::::::::::::::::::::::::::::::::::::::::::::::");

		PortalVO loginVO = (PortalVO)request.getSession().getAttribute("loginVO");

		log.info("requestURI ::  " + request.getRequestURL());

		if(loginVO == null) {
			log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: 인증 정보가 존재하지 않습니다. ::::::::::::::::::::::::::::::::::::::::::::::::::::");
			response.sendRedirect("/login");
			result = false;
		}


		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {

		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: AuthInterceptor END ::::::::::::::::::::::::::::::::::::::::::::::::::::");

	}
}