package org.seoul.kk.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RandomNamingSourceDto {
    public Long adjId;
    public String adjProperty;
    public String nounProperty;
}
