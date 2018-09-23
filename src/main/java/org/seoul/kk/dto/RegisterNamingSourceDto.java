package org.seoul.kk.dto;

import lombok.Data;
import org.seoul.kk.entity.constant.Classification;

@Data
public class RegisterNamingSourceDto {
    Classification classification;
    String property;
}
