package org.seoul.kk.service;

import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterTravelerDto;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.exception.NotFoundTraveler;

public interface TravelerService {

    Traveler getTravelerById(Long id) throws NotFoundTraveler;
    Traveler getTravelerByUuid(String uuid) throws NotFoundTraveler;
    Traveler registerTraveler(RegisterTravelerDto requestBody, String uuid) throws NotFoundTraveler;

    Object testName();
    RandomNamingReturnDto randomNaming();
    void registerTraveler(RegisterTravelerDto registerInfo);

}
