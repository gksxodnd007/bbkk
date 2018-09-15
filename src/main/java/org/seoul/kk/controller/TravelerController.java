package org.seoul.kk.controller;

import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelerController {

    private final TravelerService travelerService;

    @Autowired
    public TravelerController(
            TravelerService travelerService){
        this.travelerService = travelerService;
    }

    @GetMapping("/v1/randomNaming")
    public @ResponseBody RandomNamingReturnDto getRandomNaming(){
        return travelerService.randomNaming();
    }


    /* TODO
     * Create Traveler 할때 RandomNaming 에 보내준 ReturnDTO 에 있는
     * adjId , nounProperty 를 받아야 한다.
     * adjId로 개체를 찾은 뒤 nounProperty를 추가해준다.
     */
}
