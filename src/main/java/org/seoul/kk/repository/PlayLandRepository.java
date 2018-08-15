package org.seoul.kk.repository;

import org.seoul.kk.entity.PlayLand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayLandRepository extends JpaRepository<PlayLand, Long> {
}
