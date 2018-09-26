package org.seoul.kk.service;

import com.amazonaws.services.cloudsearchv2.model.BaseException;
import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.dto.RegisterTravelerDto;
import org.seoul.kk.entity.TravelerNaming;
import org.seoul.kk.entity.constant.Classification;
import org.seoul.kk.repository.TravelerNamingRepository;
import org.seoul.kk.repository.TravelerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNamingServiceImpl implements RandomNamingService{
    @Autowired
    private TravelerNamingRepository travelerNamingRepository;

    @Override
    public Object testName() {
        return travelerNamingRepository.findAll();
    }

    @Override
    public void registerNamingSource(RegisterNamingSourceDto namingSourceDto) {
        System.out.println(namingSourceDto.toString());
        TravelerNaming source =
                TravelerNaming.builder()
                        .classification(namingSourceDto.getClassification())
                        .property(namingSourceDto.getProperty())
                        .build();
        travelerNamingRepository.save(source);
    }


    @Override
    public RandomNamingReturnDto randomNaming() {

        // 명사 형용사 기준으로 리스트를 구분합니다.
        List<TravelerNaming> adjProperties = new ArrayList<>();
        List<TravelerNaming> nounProperties = new ArrayList<>();
        travelerNamingRepository.findAll().forEach(namingSource->{
            if(namingSource.getClassification() == Classification.ADJECTIVE)
                adjProperties.add(namingSource);
            else
                nounProperties.add(namingSource);
        });

        // 형용사 기준으로 랜덤하게 줘야 할 형용사 리스트를 만들어 놓습니다.
        List<Integer> a_list = new ArrayList<>();
        for(int i=0;i<adjProperties.size();i++) a_list.add(i);
        Collections.shuffle(a_list);

        while(true){

            Long adjTraget = Long.valueOf((int)a_list.get(0));
            TravelerNaming nowNamingObj= travelerNamingRepository.getOne(adjTraget);
            String usedNounList = nowNamingObj.getUsedList();
            int noun_size= nounProperties.size();


            for(int k=0;k<noun_size;k++)
                if(usedNounList.contains(nounProperties.get(k).getProperty())){
                    // 사용된 명사 목록에 포함 현재 명사가 포함되어 있다면 다음 명사로 넘어감
                    continue;
                }else{
                    // 만약 포함되어 있지 않은 경우
                    RandomNamingReturnDto response =
                            RandomNamingReturnDto.builder()
                                .adjId(nowNamingObj.getId())
                                .adjProperty(nowNamingObj.getProperty())
                                .nounProperty(nounProperties.get(k).getProperty())
                                .nickName(nowNamingObj.getProperty() + " " + nounProperties.get(k).getProperty())
                                .build();

                    // database에 사용했다는 것을 등록해줘야 하기 때문에
                    // 값을 리턴해줘야한다.

                    return response;
                }

            if(a_list.size() == 0) {
                throw new BaseException("사용할 수 있는 조합이 더이상 없습니다.");
            }

            // 다음 형용사 기준 조합을 생각해야함
            a_list.remove(0);
        }
    }


//    @Override
//    public void registerTraveler(RegisterTravelerDto registerInfo) {
//
//        TravelerNaming adjective = travelerNamingRepository.getOne(registerInfo.getNamingInfo().adjId);
//        String usedList = adjective.getUsedList();
//        if(!usedList.contains(registerInfo.getNamingInfo().getNounProperty())) {
//            //create!
//            String newUsedList = usedList.concat(" ").concat(registerInfo.getNamingInfo().getNounProperty());
//            adjective.setUsedList(newUsedList);
//
//            travelerNamingRepository.save(adjective);
//
//        }else{
//            // throw Error
//            // 방금 누가 선취함!
//
//        }
//
//
//    }
}
