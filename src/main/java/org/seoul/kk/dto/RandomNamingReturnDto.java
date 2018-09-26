package org.seoul.kk.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class RandomNamingReturnDto {
//    public String nounProperty;
//    public Long adjId;
//    public String adjProperty;
    public RandomNamingSourceDto randomNamingSourceDto;
    public String nickName;
}
