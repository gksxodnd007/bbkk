package org.seoul.kk.repository;

import org.seoul.kk.entity.TravelerNaming;
import org.seoul.kk.entity.constant.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelerNamingRepository extends JpaRepository<TravelerNaming,Long> {

    List<TravelerNaming> findAllByClassification(Classification classification);

}
