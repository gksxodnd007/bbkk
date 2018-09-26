package org.seoul.kk.service;

import org.seoul.kk.dto.RandomNamingSourceDto;
import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.springframework.stereotype.Service;

@Service
public interface RandomNamingService {
    Object testName();
    RandomNamingReturnDto randomNaming();
    void registerNamingSource(RegisterNamingSourceDto source);
    void registerUsedList(RandomNamingSourceDto usedListSource);
}
