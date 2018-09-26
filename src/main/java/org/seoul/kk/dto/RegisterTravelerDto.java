package org.seoul.kk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTravelerDto {

    @NotNull
    private RandomNamingSourceDto randomNamingSourceDto;
    @NotNull
    private String nickname;
    @NotNull
    private String property;

}
