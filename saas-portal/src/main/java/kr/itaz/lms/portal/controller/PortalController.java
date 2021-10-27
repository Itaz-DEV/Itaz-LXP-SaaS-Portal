package kr.itaz.lms.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import kr.itaz.lms.portal.service.PortalService;
import kr.itaz.lms.portal.vo.PortalVO;
import kr.itaz.lms.portal.vo.ServiceFareVO;
import kr.itaz.lms.portal.vo.TenantSvcVO;

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
		
		//일단 정책이 정해지지 않아 서비스 한개를 박아놓음
		List<ServiceFareVO> serviceFareList = portalService.selectServiceList("S0201002");
		model.addAttribute("serviceFareList", serviceFareList);
		
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
	 * 서비스 신청 페이지 이동
	 * @FileName : PortalController.java
	 * @Project : saas-portal
	 * @Date : 2021. 10. 20. 
	 * @작성자 : gjahn56
	 * @변경이력 :
	 * @프로그램 설명 :
	 */
	@GetMapping("/service/payment")
    public String servicePayment(HttpServletRequest request, Model model) {
		PortalVO portalVO = (PortalVO) request.getSession().getAttribute("loginVO");
		
		//내 테넌트 리스트
		List<PortalVO> tenantList = portalService.selectMyTenantList(portalVO.getUserId());
		
		//일단 정책이 정해지지 않아 서비스 한개를 박아놓음
		//과금
		List<TenantSvcVO> serviceFareList = portalService.selectServiceFareList("S0201002");
		
		model.addAttribute("serviceFareList", serviceFareList);
		model.addAttribute("tenantList", tenantList);
		model.addAttribute("svcId", request.getParameter("svcId"));
		model.addAttribute("selFareSeCode", request.getParameter("fareSeCode"));		
		
        return "portal/payment";
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
	 * 회원정보수정
	 * @Method Name : updateUserInformation
	 * @작성일 : 2021. 10.26.
	 * @작성자 : gjahn56
	 * @변경이력 : 
	 * @Method 설명 :
	 * @param protalVO
	 * @return
	*/
	@PostMapping("/userinfo/update")
    public ResponseEntity<?> updateUserInformation(HttpServletRequest request, Model model, @RequestBody PortalVO protalVO) {
		PortalVO loginVO = (PortalVO) request.getSession().getAttribute("loginVO");
		Map<String, String> messageMap = new HashMap<String, String>();
		
		protalVO.setUserNo(loginVO.getUserNo());
		protalVO.setUserId(loginVO.getUserId());
		protalVO.setCreatIp(this.getIp(request));
		portalService.updateUserInformation(request, protalVO);

		messageMap.put("message", "수정이 완료되었습니다.");
        return ResponseEntity.ok().body(messageMap);
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
    public String mypage(HttpServletRequest request, Model model) {
		PortalVO portalVO = (PortalVO) request.getSession().getAttribute("loginVO");
		List<PortalVO> tenantList = portalService.selectMyTenantList(portalVO);
		
		model.addAttribute("tenantList", tenantList);
		model.addAttribute("userInfo", portalVO);
		
		if(tenantList != null) {
			List<TenantSvcVO> tenantServiceList = portalService.selectMyTenantServiceList(portalVO);
			model.addAttribute("tenantServiceList", tenantServiceList);
		}
		return "portal/mypage";
    }
	
	/**
	 * 마이페이지 로드 전 비밀번호 체크
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
	@GetMapping("/mypage/passwordChk")
    public ResponseEntity<Integer> mypagePasswordChk(HttpServletRequest request, Model model, String password) {
		PortalVO portalVO = (PortalVO) request.getSession().getAttribute("loginVO");
		
		portalVO.setPassword(password);
		int passwordChk = portalService.passwordCheck(portalVO);

		model.addAttribute("passwordChk", passwordChk);		
		
		return ResponseEntity.ok().body(passwordChk);
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
    public String tenant(HttpServletRequest request, Model model, Optional<String> tenantId) {
		//테넌트 분류 조회
		List<PortalVO> tenantClList = portalService.selectCommCodeList("C002");		//테넌트 분류 리스트
		model.addAttribute("tenantClList", tenantClList);
		
		if (!tenantId.isEmpty()) {
			PortalVO loginVO = (PortalVO) request.getSession().getAttribute("loginVO");
			loginVO.setTenantId(tenantId.get());
			List<PortalVO> tenantList = portalService.selectMyTenantList(loginVO);
			model.addAttribute("tenant", tenantList.get(0));
		}
				
		return "portal/tenant";
    }
	
	/**
	 * 테넌트 수정
	 * @Method Name : tenantUpdate
	 * @작성일 : 2021. 10. 17.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 17.
	 * @Method 설명 : 
	 * @param request
	 * @param model
	 * @param portalVO
	 * @return
	 */
	@PostMapping("/tenant/update")
    public ResponseEntity<?> tenantUpdate(HttpServletRequest request, Model model, @RequestBody PortalVO potalVO) {
		Map<String, String> messageMap = new HashMap<String, String>();
		PortalVO loginVO = (PortalVO) request.getSession().getAttribute("loginVO");
		potalVO.setUserId(loginVO.getUserId());
		potalVO.setCreatIp(this.getIp(request));
		
		portalService.updateTenantInfo(potalVO);
		
		messageMap.put("message", "수정이 완료되었습니다.");
			
		return ResponseEntity.ok().body(messageMap);
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
	 * 테넌트 등록
	 * @Method Name : tenantInsert
	 * @작성일 : 2021. 10. 25.
	 * @작성자 : gjahn56
	 * @변경이력 : 2021. 10. 25.
	 * @Method 설명 : 
	 * @param portalVO
	 * @return
	 */
	@PostMapping("/tenant/insert")
    public String tenantInsert(HttpServletRequest request, Model model, PortalVO portalVO){
		PortalVO loginVO = (PortalVO) request.getSession().getAttribute("loginVO");
		portalVO.setUserNo(loginVO.getUserNo());
		portalVO.setUserId(loginVO.getUserId());
		portalVO.setCreatIp(this.getIp(request));
		
		portalService.insertTenantInfo(portalVO);
        return "redirect:/mypage#tab02";
    }
	
	/**
	 * 서비스 결제 등록
	 * @Method Name : tenantServiceInsert
	 * @작성일 : 2021. 10. 25.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 25.
	 * @Method 설명 : 
	 * @param tenantSvcVO
	 * @return
	 */
	@PostMapping(value = "/service/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> tenantServiceInsert(HttpServletRequest request, Model model, @RequestBody TenantSvcVO tenantSvcVO){
		PortalVO loginVO = (PortalVO) request.getSession().getAttribute("loginVO");
		tenantSvcVO.setCreatId(loginVO.getUserId());
		tenantSvcVO.setCreatIp(this.getIp(request));
		Map<String, String> messageMap = new HashMap<String, String>();
		
		try {
			portalService.tenantServiceInsert(tenantSvcVO);
			messageMap.put("message", "서비스 결제가 완료되었습니다.");
		} catch (DuplicateKeyException e) {
			messageMap.put("message", "이미 등록된 서비스입니다.");
			return new ResponseEntity<Map>(messageMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
        return new ResponseEntity<Map>(messageMap, HttpStatus.OK);
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
