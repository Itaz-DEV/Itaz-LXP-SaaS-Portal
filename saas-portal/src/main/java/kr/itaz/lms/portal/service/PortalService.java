package kr.itaz.lms.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.itaz.lms.portal.vo.PortalVO;
import kr.itaz.lms.portal.vo.ServiceFareVO;
import kr.itaz.lms.portal.vo.TenantSvcVO;

public interface PortalService {

	/**
	 * 공통코드 조회
	 * @Method Name : selectCommCodeist
	 * @작성일 : 2021. 10. 16.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 16.
	 * @Method 설명 : 공통코드 목록을 조회하기 위한 공통 메소드
	 * @param code
	 * @return
	 */
	public List<PortalVO> selectCommCodeList(String code);
	
	/**
	 * 회원가입 입력
	 * @Method Name : insertUserInformation
	 * @작성일 : 2021. 10. 16.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 16.
	 * @Method 설명 :
	 * @param portalVO
	 */
	public void insertUserInformation(PortalVO portalVO);
	
	/**
	 * 이메일 중복체크 여부
	 * @Method Name : selectCheckUuplicateEmail
	 * @작성일 : 2021. 10. 16.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 16.
	 * @Method 설명 : 
	 * @param userId
	 * @return
	 */
	public int selectCheckDuplicateEmail(String userId);
	
	/**
	 * 
	 * @Method Name : login
	 * @작성일 : 2021. 10. 16.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 16.
	 * @Method 설명 : 
	 * @param portalVO
	 * @return
	 */
	public int login(HttpServletRequest request, PortalVO portalVO);
	
	/**
	 * 나의 테넌트 정보
	 * @Method Name : selectMyTenantInfo
	 * @작성일 : 2021. 10. 17.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 17.
	 * @Method 설명 : 
	 * @param portalVO
	 * @return
	 */
	public List<PortalVO> selectMyTenantList(PortalVO portalVO);
	
	/**
	 * 나의 테넌트에서 신청한 서비스 목록
	 * @Method Name : selectMyTenantServiceList
	 * @작성일 : 2021. 10. 17.
	 * @작성자 : itaz-bnso
	 * @변경이력 : 2021. 10. 17.
	 * @Method 설명 : 
	 * @param portalVO
	 * @return
	 */
	public List<PortalVO> selectMyTenantServiceList(PortalVO portalVO);
	
	/**
	 * 서비스 과금 목록
	 * @Method Name : selectMyTenantServiceList
	 * @작성일 : 2021. 10. 20.
	 * @작성자 : gjahn56
	 * @변경이력 : 2021. 10. 20.
	 * @Method 설명 : 
	 * @param svcId
	 * @return
	 */
	public List<ServiceFareVO> selectServiceList(String svcId);
	
	/**
	 * 테넌트 정보
	 * @Method Name : selectMyTenantList
	 * @작성일 : 2021. 10. 21.
	 * @작성자 : gjahn56
	 * @변경이력 : 2021. 10. 21.
	 * @Method 설명 : 
	 * @param userId
	 * @return
	 */
	public List<PortalVO> selectMyTenantList(String userId);
	
	/**
	 * 서비스 과금 단위 정보
	 * @Method Name : selectServiceFareList
	 * @작성일 : 2021. 10. 21.
	 * @작성자 : gjahn56
	 * @변경이력 : 2021. 10. 21.
	 * @Method 설명 : 
	 * @param userId
	 * @return
	 */
	public List<TenantSvcVO> selectServiceFareList(String svcId);
	
	/**
	 * 테넌트 서비스 등록
	 * @Method Name : tenantServiceInsert
	 * @작성일 : 2021. 10. 21.
	 * @작성자 : gjahn56
	 * @변경이력 : 2021. 10. 21.
	 * @Method 설명 : 
	 * @param userId
	 * @return
	 */
	public void tenantServiceInsert(TenantSvcVO tenantSvcVO);	
	
}
