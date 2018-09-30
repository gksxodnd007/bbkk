package org.seoul.kk.dto.traveler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seoul.kk.dto.RandomNamingSourceDto;

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
