package kr.itaz.lms.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.itaz.lms.sample.service.SampleService;

@Controller
public class PortalController {
	
	@Autowired
	SampleService sampleService;
	
	/**
	 * 디폴트 경로
	 * @FileName : PortalController.java
	 * @Project : saas-portal
	 * @Date : 2021. 10. 13. 
	 * @작성자 : ITAZ_D
	 * @변경이력 :
	 * @프로그램 설명 :
	 */
	@GetMapping("/")
    public String index(Model model) {
        return "redirect:/main";
    }
	
	/**
	 * 메인 화면
	 * @FileName : PortalController.java
	 * @Project : saas-portal
	 * @Date : 2021. 10. 13. 
	 * @작성자 : ITAZ_D
	 * @변경이력 :
	 * @프로그램 설명 :
	 */
	@GetMapping("/main")
    public String main(Model model) {
        return "portal/main";
    }
	
	/**
	 * 회원가입 페이지 이동
	 * @FileName : PortalController.java
	 * @Project : saas-portal
	 * @Date : 2021. 10. 13. 
	 * @작성자 : ITAZ_D
	 * @변경이력 :
	 * @프로그램 설명 :
	 */
	@GetMapping("/joinMember")
    public String joinMember(Model model) {
        return "portal/join";
    }
	
	@GetMapping("/login")
    public String login(Model model) {
        return "portal/login";
    }
}
