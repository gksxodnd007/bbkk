package org.seoul.kk.controller;

import org.apache.commons.codec.binary.Base64;
import org.seoul.kk.dto.RegisterPlayLandDto;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.exception.NotFoundTraveler;
import org.seoul.kk.service.PlayLandService;
import org.seoul.kk.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
public class PlayLandController {

    private final PlayLandService playLandService;
    private final TravelerService travelerService;

    @Autowired
    public PlayLandController(PlayLandService playLandService,
                              TravelerService travelerService) {
        this.playLandService = playLandService;
        this.travelerService = travelerService;
    }

    //TODO 이미지 5장 초과시
    @PostMapping(value = "/v1/register/playland", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void registerPlayLand(@Valid @RequestBody RegisterPlayLandDto requestBody,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("PlayLand를 등록시 필수 파라미터를 채워주세요");
        }

        if (!isBase64Encoded(requestBody.getImages())) {
            throw new BadRequestException("이미지가 base64로 인코딩 되지않았습니다.");
        }

        if (requestBody.getImages().split(",").length > 5) {
            throw new BadRequestException("이미지를 5장이상 업로드 할 수 없습니다.");
        }

        Traveler traveler = travelerService.getTravelerById(requestBody.getTravelerId()).orElseThrow(NotFoundTraveler::new);
        playLandService.registerPlayLand(requestBody, traveler);
    }

    private boolean isBase64Encoded(String images) {
        return Arrays.stream(images.split(",")).allMatch(Base64::isBase64);
    }

}
