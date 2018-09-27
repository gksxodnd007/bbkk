package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.review.FeedReviewDto;
import org.seoul.kk.dto.review.ReqReviewDto;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/react")
public class ReactController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping(value = "/review/playland")
    public ApiResponseModel<FeedReviewDto> reviewToPlayLand(@Valid @RequestBody ReqReviewDto requestBody,
                                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        return ApiResponseModel.<FeedReviewDto>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(reviewService.reviewToPlayLand(requestBody))
                .build();
    }
}