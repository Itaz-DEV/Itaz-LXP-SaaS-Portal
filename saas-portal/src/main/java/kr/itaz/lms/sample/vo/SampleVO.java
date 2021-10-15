package kr.itaz.lms.sample.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleVO {
	 
    private String userId;
    private String userPwd;
    private String name;
    private String authType;
    private String tenantId;
 
}
