package org.seoul.kk.repository;

import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.constant.Season;

import java.util.List;

public interface PlayLandCustomRepository {

    List<PlayLand> findPlayLandBySeasonOrderByReviewCnt(Season season);
    List<PlayLand> findPlayLandOrderByReviewCntLimit(long limitSize);
    List<PlayLand> findPlayLandOrderByCreatedAtFromCursorLimit(long cursor, long size);
}
