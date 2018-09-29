package org.seoul.kk.controller;

import org.seoul.kk.dto.review.ReqBookMarkDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/bookmark")
public class BookMarkController {

    @PostMapping
    public void bookmarkPlayLand(@Valid @RequestBody ReqBookMarkDto requestBody) {
        
    }
}
