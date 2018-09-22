package org.seoul.kk.repository;

import org.junit.Test;
import org.seoul.kk.SpringTestSupport;
import org.seoul.kk.entity.TravelerNaming;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelerNamingRepoTests extends SpringTestSupport {
    @Autowired
    private TravelerNamingRepository travelerNamingRepository;

    @Test
    public void test(){
//        travelerNamingRepository.findTravelerNamingsByClassification(1)
        TravelerNaming trname = new TravelerNaming();
        trname.setClassification(1);
        trname.setProperty("형용사1");
        travelerNamingRepository.save(trname);
    }
}
