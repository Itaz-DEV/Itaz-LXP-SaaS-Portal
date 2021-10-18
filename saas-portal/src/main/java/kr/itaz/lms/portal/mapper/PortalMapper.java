package kr.itaz.lms.portal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.itaz.lms.portal.vo.PortalVO;
import kr.itaz.lms.sample.vo.SampleVO;

@Mapper
public interface PortalMapper {
	
	public List<PortalVO> selectCommCodeList(String code);
	public void insertUserInformation(PortalVO portalVO);
	public int selectUserCheck(String userId);
	public PortalVO selectUserInfo(PortalVO portalVO);

	public List<PortalVO> selectMyTenantList(PortalVO portalVO);
	public List<PortalVO> selectMyTenantServiceList(PortalVO portalVO);
}
