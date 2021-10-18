package kr.itaz.lms.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.itaz.lms.portal.mapper.PortalMapper;
import kr.itaz.lms.portal.vo.PortalVO;

@Service
public class PortalServiceImpl implements PortalService{
	
	@Autowired
	PortalMapper portalMapper;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public List<PortalVO> selectCommCodeList(String code){
		return portalMapper.selectCommCodeList(code);
	}
	
	public void insertUserInformation(PortalVO portalVO){
		portalVO.setPassword(passwordEncoder.encode(portalVO.getPassword()));
		portalMapper.insertUserInformation(portalVO);
	}
	
	public int selectCheckDuplicateEmail(String userId){
		return portalMapper.selectUserCheck(userId);
	}
	
	public int login(HttpServletRequest request, PortalVO portalVO){
		 
		int isSuccess = 0;
		try {
			//비밀번호 암호화해서 유저 정보 조회 
			PortalVO loginVO = portalMapper.selectUserInfo(portalVO);
			
			if(loginVO != null) {
				if(passwordEncoder.matches(portalVO.getPassword(), loginVO.getPassword())) {
					request.getSession().setAttribute("loginVO", loginVO);
					isSuccess = 0;		// 성공
				}else {
					isSuccess = 1;		// 비밀번호 맞지 않음
				}
			}else {
				isSuccess = 1;		// 정보 없음
			}
			
			if(isSuccess == 0) {
				//portalMapper.selectCheckCreateTenantInfo(portalVO);
			}
		}catch(Exception e) {
			return isSuccess = 2;	//오류
		}
		
		return isSuccess;
	}
	
	public List<PortalVO> selectMyTenantList(PortalVO portalVO) {
		return portalMapper.selectMyTenantList(portalVO);
	}
	
	public List<PortalVO> selectMyTenantServiceList(PortalVO portalVO){
		return portalMapper.selectMyTenantServiceList(portalVO);
	}
}
