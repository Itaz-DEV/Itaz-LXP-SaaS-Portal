package kr.itaz.lms.portal.vo;

import javax.validation.constraints.NotBlank;

import kr.itaz.lms.core.ValidationGroups;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TenantSvcVO {
	@NotBlank(groups = {ValidationGroups.insert.class}) 
	private String tenantId; //테넌트 id
	
	@NotBlank(groups = {ValidationGroups.insert.class}) 
	private String svcId; //서비스 id
	
	private String svcNmKor; //서비스 명
	
	@NotBlank(groups = {ValidationGroups.insert.class}) 
	private String svcSeCode; //서비스 구분
	
	@NotBlank(groups = {ValidationGroups.insert.class}) 
	private String svcAtrbCode; //시비스속성
	
	private String fareCode;//서비스 과금제 코드
	
	private String fareSeCode; //과금제 코드
	
	private String fareSeCodeNm; //과금제 코드명
	
	private String fareUnitCode; //과금단위 코드
	
	private String fareUnitCodeNm; //과금단위 코드명
	
	private Integer fareAmount;//과금 금액
	
	private Integer useAt; //사용여부
	
	private String startDt; //사용시작기간
	private String endDt; //사용종료기간
	
	private String creatId;
	private String creatIp;
}
