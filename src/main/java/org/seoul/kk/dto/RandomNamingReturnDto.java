package org.seoul.kk.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class RandomNamingReturnDto {
    public Long adjId;
    public String adjProperty;
    public String nounProperty;
    public String nickName;
}
