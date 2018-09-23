package org.seoul.kk.dto;

import lombok.Getter;
import lombok.Setter;
import org.seoul.kk.entity.constant.TravelProperty;

@Getter
@Setter
public class RegisterTravelerDto {
    private RandomNamingReturnDto namingInfo;
    private String nickName;
    private TravelProperty travelerProperty;


}
