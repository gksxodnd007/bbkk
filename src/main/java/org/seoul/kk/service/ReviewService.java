package org.seoul.kk.service;

import org.seoul.kk.dto.review.FeedReviewDto;
import org.seoul.kk.dto.review.ReqReviewDto;

public interface ReviewService {

    FeedReviewDto reviewToPlayLand(ReqReviewDto requestBody);
}
