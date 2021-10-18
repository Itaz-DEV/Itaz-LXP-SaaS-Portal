package kr.itaz.lms.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.itaz.lms.portal.service.PortalService;
import kr.itaz.lms.portal.vo.PortalVO;

@Controller
public class PortalController {
	
	@Autowired
	PortalService portalService;
	
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
    public String main(HttpServletRequest request, Model model) {
		PortalVO loginVO = (PortalVO) request.getSession().getAttribute("loginVO");
		System.out.println("request Session :: " + request.getSession().getAttribute("loginVO"));
		if(loginVO != null){
			System.out.println("request Session  getUserId:: " + loginVO.getUserId());
		}
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
	@GetMapping("/join/joinMember")
    public String joinMember(Model model) {
        return "portal/join";
    }
	
	/**
	 * 사용자아이디(이메일) 중복체크
	 * @Method Name : isSetServiceURL
	 * @작성일 : 2021. 10. 16.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 16.
	 * @Method 설명 : 
	 * @param serviceURL
	 * @return
	 */
	@GetMapping("/join/checkDuplicateEmail")
    public ResponseEntity<?> checkDuplicateEmail(String userId){
		int checkCnt = portalService.selectCheckDuplicateEmail(userId);
        return ResponseEntity.ok().body(checkCnt);
    }
	
	/**
	 * 회원가입 처리
	 * @FileName : PortalController.java
	 * @Project : saas-portal
	 * @Date : 2021. 10. 13. 
	 * @작성자 : ITAZ_D
	 * @변경이력 :
	 * @프로그램 설명 :
	 */
	@PostMapping("/join/joinMember")
    public String joinMemberPrcess(HttpServletRequest request, Model model, PortalVO protalVO) {
		protalVO.setCreatIp(this.getIp(request));
		portalService.insertUserInformation(protalVO);
        return "redirect:/login";
    }

	/**
	 * 로그인 화면 이동
	 * @Method Name : login
	 * @작성일 : 2021. 10. 15.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 
	 * @Method 설명 :
	 * @param model
	 * @return
	*/
	@GetMapping("/login")
    public String login(Model model) {
        return "portal/login";
    }
	
	/**
	 *  로그인 처리
	 * @Method Name : login
	 * @작성일 : 2021. 10. 15.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 
	 * @Method 설명 :
	 * @param model
	 * @return
	*/
	@GetMapping("/loginProcess")
    public ResponseEntity<Integer> loginProcess(HttpServletRequest request, Model model, PortalVO portalVO) {
		int isSuccess = portalService.login(request, portalVO);
        return ResponseEntity.ok().body(isSuccess);
    }
	
	/**
	 *  로그아웃 처리
	 * @Method Name : login
	 * @작성일 : 2021. 10. 15.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 
	 * @Method 설명 :
	 * @param model
	 * @return
	*/
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model, PortalVO portalVO) {
		request.getSession().invalidate();
		return "redirect:/main";
    }
	
	/**
	 * 마이페이지
	 * 테넌트가 없으면 테넌트 생성, 본인의 테넌트 및 서비스 신청 목록이 있으면 서비스 신청 목록을 보여줌
	 * @Method Name : logout
	 * @작성일 : 2021. 10. 17.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 17.
	 * @Method 설명 : 
	 * @param request
	 * @param model
	 * @param portalVO
	 * @return
	 */
	@GetMapping("/mypage")
    public String mypage(Model model, PortalVO portalVO) {
		
		List<PortalVO> tenantList = portalService.selectMyTenantList(portalVO);
		model.addAttribute("tenantList", tenantList);
		
		if(tenantList != null) {
			List<PortalVO> tenantServiceList = portalService.selectMyTenantServiceList(portalVO);
			model.addAttribute("tenantServiceList", tenantServiceList);
		}
		
		return "portal/mypage";
    }
	
	/**
	 * 테넌트 생성 화면 이동
	 * @Method Name : logout
	 * @작성일 : 2021. 10. 17.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 17.
	 * @Method 설명 : 
	 * @param request
	 * @param model
	 * @param portalVO
	 * @return
	 */
	@GetMapping("/tenant")
    public String tenant(HttpServletRequest request, Model model, PortalVO portalVO) {
		//테넌트 분류 조회
		List<PortalVO> tenantClList = portalService.selectCommCodeList("C002");		//테넌트 분류 리스트
		model.addAttribute("tenantClList", tenantClList);
				
		return "portal/tenant";
    }
	
	/**
	 * 테넌트 상세 분류
	 * @Method Name : checkDuplicateEmail
	 * @작성일 : 2021. 10. 17.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 17.
	 * @Method 설명 : 
	 * @param userId
	 * @return
	 */
	@GetMapping("/tenantDetailClList")
    public ResponseEntity<?> tenantDetailClList(String tenantCl){
		List<PortalVO> tenantDetailClList = portalService.selectCommCodeList(tenantCl);
        return ResponseEntity.ok().body(tenantDetailClList);
    }
	
	/**
	 * 클라이언트 아이디 조회
	 * @Method Name : getIp
	 * @작성일 : 2021. 10. 16.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 16.
	 * @Method 설명 : 
	 * @param request
	 * @return
	 */
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = request.getRemoteAddr();
		return ip;
	}
}
