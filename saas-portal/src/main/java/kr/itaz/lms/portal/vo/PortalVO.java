package kr.itaz.lms.portal.vo;

import javax.validation.constraints.NotBlank;

import kr.itaz.lms.core.ValidationGroups;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortalVO {
	 
    private String code;
    private String codeNmKor;

    //사용자 정보
    @NotBlank(groups = {ValidationGroups.insert.class}) 
    private String userId;
    @NotBlank(groups = {ValidationGroups.insert.class}) 
    private String password;
	private String telNo;
	@NotBlank(groups = {ValidationGroups.insert.class}) 
	private String userName;
	private String creatIp;
	private String userNo;
	
	
	private String tenantId; 			//테넌트 아이디
	private String tenantCl;			//테넌트 분류
	private String tenantClNm;			//테넌트 분류명
	private String tenantDtlCl;			//테넌트 상세 분류
	private String tenantDtlClNm;		//테넌트 상세 분류 명
	private String tenantNmKor;			//테넌트 국문명
	private String tenantNmEng;			//테넌트 영문명
	private String tenantReprsntId;		//테넌트 대표 아이디(서비스 도메인 사용)
	private String reprsntEmail;		//테넌트 이메일
	private String zipNo;				//우편번호
	private String address;				//주소
	private String reprsntTelno;		//대표 전화번호
	private String chargerId;			//테넌트 담당자 아이디
	private String chargerNm;			//테넌트 담당자 명
	private String chargerMobile;		//테넌트 담당자 휴대전화번호
}
