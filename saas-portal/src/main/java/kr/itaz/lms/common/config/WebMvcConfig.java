package kr.itaz.lms.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.itaz.lms.common.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 제외 시킬 URI
	private final String[] excludePathPatterns = {
			"/error",
			"/favicon.ico",			// 파비콘
			"/",					// 최 상위 컨텍스트
			"/login",				// 첫 페이지
			"/loginProcess",
			"/auth/**",				// 인증 관련
			"/css/**",				// 정적 파일 CSS
			"/js/**",				// 정적 파일 JS
			"/bootstrap/**",		// 정적 파일 BOOTSTRAP
			"/main",
			"/join/**",
			"/img/**",
			"/main"
	};
	
	@Autowired
	private AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
	}
}