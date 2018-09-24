package org.seoul.kk.service;

import org.seoul.kk.dto.FeedPlayLandDto;
import org.seoul.kk.dto.RegisterPlayLandDto;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;

public interface PlayLandService {

    void registerPlayLand(RegisterPlayLandDto registerPlayLandDto, Traveler traveler);
    PlayLand updatePlayLand(PlayLand requestBody);
    void deletePlayLand(Long id);
    FeedPlayLandDto feedPlayLand(long cursor, long size, boolean rankFlag, long rankDataSize);
}
