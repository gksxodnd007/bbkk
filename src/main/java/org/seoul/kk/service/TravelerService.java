package org.seoul.kk.service;

import org.seoul.kk.entity.Traveler;

import java.util.Optional;

public interface TravelerService {

    Optional<Traveler> getTravelerById(Long id);

}
