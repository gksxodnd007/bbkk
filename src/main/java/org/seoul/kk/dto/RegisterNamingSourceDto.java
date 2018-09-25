package org.seoul.kk.dto;

import lombok.Data;
import org.seoul.kk.entity.constant.Classification;

import javax.validation.constraints.NotNull;

@Data
public class RegisterNamingSourceDto {
    @NotNull(message = "classifiaction is null")
    Classification classification;
    @NotNull(message = "property is null")
    String property;
}
