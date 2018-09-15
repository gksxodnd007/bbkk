package org.seoul.kk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTravelerDto {
    private RandomNamingReturnDto namingInfo;
    private String nickName;
    private Object travelerProperty;
    // TODO property 에 관한 얘기를 나눠야 한다.


}
