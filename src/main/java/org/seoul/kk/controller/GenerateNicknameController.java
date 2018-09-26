package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.dto.TempNickname;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.RandomNamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1")
public class GenerateNicknameController {

    @Autowired
    private RandomNamingService randomNamingService;


    @GetMapping(value = "/generate/nickname")
    public ApiResponseModel<TempNickname> generateNicknameTemp() {
        return ApiResponseModel.<TempNickname>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(new TempNickname("코딩하는 오징어"))
                .build();
    }

    @PostMapping(value = "/generate/source", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void generateRandomSource(
            @Valid @RequestBody RegisterNamingSourceDto sourceDto
            ,BindingResult bindingResult
        ){
            if(bindingResult.hasErrors()){
                throw new BadRequestException("Random source 필수 파라미터를 채워주세요");
            }
            randomNamingService.registerNamingSource(sourceDto);
    }

//    @PostMapping(value = "/v1/randomsource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public @ResponseBody
//    void addRandomSource(@Valid @RequestBody RegisterNamingSourceDto sourceDto,
//                         BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            throw new BadRequestException("Random source 필수 파라미터를 채워주세요");
//        }
//        travelerService.registerNamingSource(sourceDto);
//    }
//    // review here
//    // consume 을 명시했는데도 @RequestBody 사용하면 동작하지 않음
}
