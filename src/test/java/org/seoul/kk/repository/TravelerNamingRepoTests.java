package org.seoul.kk.repository;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.seoul.kk.SpringTestSupport;
import org.seoul.kk.entity.TravelerNaming;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelerNamingRepoTests extends SpringTestSupport {
    @Autowired
    private TravelerNamingRepository travelerNamingRepository;

    @BeforeEach
    public void insTest(){
        TravelerNaming trname = new TravelerNaming();
        trname.setClassification(1);
        trname.setProperty("형용사1");
        travelerNamingRepository.save(trname);
        trname.setClassification(2);
        trname.setProperty("명사1");
        travelerNamingRepository.save(trname);
    }

    @AfterEach
    void delAllNameSource(){
        travelerNamingRepository.deleteAll();
    }

    @Test
    public void test(){
        travelerNamingRepository.findAll().forEach(nam->{
            System.out.println(nam);
        });
    }
}
