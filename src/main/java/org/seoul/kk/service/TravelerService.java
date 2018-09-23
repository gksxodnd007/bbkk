package org.seoul.kk.service;

import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.dto.RegisterTravelerDto;
import org.seoul.kk.entity.Traveler;

import java.util.Optional;

public interface TravelerService {

    void registerTraveler(RegisterTravelerDto registerInfo);
    void registerNamingSource(RegisterNamingSourceDto namingSourceDto);
    RandomNamingReturnDto randomNaming();
    Optional<Traveler> getTravelerById(Long id);

    Object testName();

}
