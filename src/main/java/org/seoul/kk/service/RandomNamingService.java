package org.seoul.kk.service;

import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.dto.RegisterTravelerDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public interface RandomNamingService {
    Object testName();
    RandomNamingReturnDto randomNaming();
    void registerNamingSource(RegisterNamingSourceDto source);
//    void registerTraveler(RegisterTravelerDto registerInfo);
}
