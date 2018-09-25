package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.TempNickname;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class GenerateNicknameController {

    @GetMapping(value = "/generate/nickname")
    public ApiResponseModel<TempNickname> generateNicknameTemp() {
        return ApiResponseModel.<TempNickname>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(new TempNickname("코딩하는 오징어"))
                .build();
    }
}
