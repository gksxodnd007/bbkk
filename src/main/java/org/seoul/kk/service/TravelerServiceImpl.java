package org.seoul.kk.service;

import org.seoul.kk.entity.Traveler;
import org.seoul.kk.repository.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelerServiceImpl implements TravelerService {

    private TravelerRepository travelerRepository;

    public TravelerServiceImpl(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    @Override
    public Optional<Traveler> getTravelerById(Long id) {
        return travelerRepository.findById(id);
    }

}
