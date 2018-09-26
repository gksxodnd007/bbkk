package org.seoul.kk.controller;

import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TravelerController {

    private final TravelerService travelerService;

    @Autowired
    public TravelerController(
            TravelerService travelerService){
        this.travelerService = travelerService;
    }

    @GetMapping("/v1/randomnaming")
    public @ResponseBody RandomNamingReturnDto getRandomNaming(){
        return travelerService.randomNaming();
    }

    // TODO remove this test code
    @GetMapping("/v1/getname")
    public @ResponseBody Object getAllNameSource(){

        return travelerService.testName();
    }

    @PostMapping(value = "/v1/randomsource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody void addRandomSource(@Valid @RequestBody RegisterNamingSourceDto sourceDto,
                                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException("Random source 필수 파라미터를 채워주세요");
        }
        travelerService.registerNamingSource(sourceDto);
    }
    // review here
    // consume 을 명시했는데도 @RequestBody 사용하면 동작하지 않음



    /* TODO
     * Create Traveler 할때 RandomNaming 에 보내준 ReturnDTO 에 있는
     * adjId , nounProperty 를 받아야 한다.
     * adjId로 개체를 찾은 뒤 nounProperty를 추가해준다.
     */
}
