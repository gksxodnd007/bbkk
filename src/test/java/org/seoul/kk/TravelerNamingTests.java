package org.seoul.kk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seoul.kk.entity.TravelerNaming;
import org.seoul.kk.repository.TravelerNamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class TravelerNamingTests {
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
