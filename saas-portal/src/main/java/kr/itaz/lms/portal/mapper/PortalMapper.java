package kr.itaz.lms.portal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.itaz.lms.portal.vo.PortalVO;
import kr.itaz.lms.portal.vo.ServiceFareVO;
import kr.itaz.lms.portal.vo.TenantSvcVO;
import kr.itaz.lms.sample.vo.SampleVO;

@Mapper
public interface PortalMapper {
	
	public List<PortalVO> selectCommCodeList(String code);
	public void insertUserInformation(PortalVO portalVO);
	public int selectUserCheck(String userId);
	public PortalVO selectUserInfo(PortalVO portalVO);
	public void updateUserInfo(PortalVO portalVO);

	public void insertTenantInfo(PortalVO portalVO);
	public void updateTenantInfo(PortalVO portalVO);
	public void insertTenantUser(PortalVO portalVO);
	public List<PortalVO> selectMyTenantList(PortalVO portalVO);
	public List<TenantSvcVO> selectMyTenantServiceList(PortalVO portalVO);
	
	public List<ServiceFareVO> selectServiceList(String svcId);
	public List<PortalVO> selectMyTenantComboList(String userId);
	public List<TenantSvcVO> selectServiceFareList(String svcId);
	public void tenantServiceInsert(TenantSvcVO tenantSvcVO);
	
}
