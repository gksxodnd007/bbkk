package org.seoul.kk.service;

import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.dto.RegisterTravelerDto;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.TravelerNaming;
import org.seoul.kk.entity.constant.Classification;
import org.seoul.kk.repository.TravelerNamingRepository;
import org.seoul.kk.repository.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TravelerServiceImpl implements TravelerService {

    private TravelerRepository travelerRepository;
    private TravelerNamingRepository travelerNamingRepository;

    public TravelerServiceImpl(
            TravelerRepository travelerRepository ,
            TravelerNamingRepository travelerNamingRepository
    ) {
        this.travelerRepository = travelerRepository;
        this.travelerNamingRepository = travelerNamingRepository;
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
    public Object testName() {
        return travelerNamingRepository.findAll();
    }

    @Override
    public RandomNamingReturnDto randomNaming() {

        RandomNamingReturnDto response = new RandomNamingReturnDto();

        int adjSize = 100;
        // adj list => classification 1 의 사이즈

        List<Integer> a_list = new ArrayList<>();
        for(int i=0;i<adjSize;i++) a_list.add(i);
        Collections.shuffle(a_list);

        List<TravelerNaming> nounProperties = travelerNamingRepository.findTravelerNamingsByClassification(Classification.NOUN);

        while(true){
//            boolean nameFounded = false;
            Long adjTraget = Long.valueOf((int)a_list.get(0));
            TravelerNaming nowNamingObj= travelerNamingRepository.getOne(adjTraget);
            String usedNounList = nowNamingObj.getUsedList();
            int noun_size= nounProperties.size();

            for(int k=0;k<noun_size;k++)
                if(usedNounList.contains(nounProperties.get(k).getProperty())){
                    continue;
                    // 없어도 상관없음
                }else{
                    response.setAdjProperty(nowNamingObj.getProperty());
                    response.setAdjId(nowNamingObj.getId());
                    response.setNounProperty(nounProperties.get(k).getProperty());
                    // database에 사용했다는 것을 등록해줘야 하기 때문에
                    // 값을 리턴해줘야한다.

//                    nameFounded = true;
                    return response;

                }

//            if(nameFounded) break;
            if(a_list.size() == 0) {
                break;
                // throw new Error
                // 모든 조합을 다 확인했지만 없음
            }
            a_list.remove(0);
        }

        // 여기에 도달하는 경우는 없음 ( 그래야함... )
        return response;
    }

    @Override
    public void registerTraveler(RegisterTravelerDto registerInfo) {

        TravelerNaming adjective = travelerNamingRepository.getOne(registerInfo.getNamingInfo().adjId);
        String usedList = adjective.getUsedList();
        if(!usedList.contains(registerInfo.getNamingInfo().getNounProperty())) {
            //create!
            String newUsedList = usedList.concat(" ").concat(registerInfo.getNamingInfo().getNounProperty());
            adjective.setUsedList(newUsedList);

            travelerNamingRepository.save(adjective);

        }else{
            // throw Error
            // 방금 누가 선취함!

        }


    }

    @Override
    public Optional<Traveler> getTravelerById(Long id) {
        return travelerRepository.findById(id);
    }

}
