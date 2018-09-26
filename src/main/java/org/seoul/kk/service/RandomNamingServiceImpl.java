package org.seoul.kk.service;

import com.amazonaws.services.cloudsearchv2.model.BaseException;
import org.seoul.kk.dto.RandomNamingSourceDto;
import org.seoul.kk.dto.RandomNamingReturnDto;
import org.seoul.kk.dto.RegisterNamingSourceDto;
import org.seoul.kk.entity.TravelerNaming;
import org.seoul.kk.entity.constant.Classification;
import org.seoul.kk.exception.ErrorModel;
import org.seoul.kk.repository.TravelerNamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNamingServiceImpl implements RandomNamingService{
    @Autowired
    private TravelerNamingRepository travelerNamingRepository;

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
    public void registerUsedList(RandomNamingSourceDto usedListSource) {
        TravelerNaming travelerNaming = travelerNamingRepository.getOne(usedListSource.getAdjId());
        String usedNounList = travelerNaming.getUsedList();

        if(usedNounList.contains(usedListSource.getNounProperty()))
            throw new BaseException("이미 사용된 형용사 명사 조합입니다.");

        String newUsedNounList = usedNounList.concat(" " + usedListSource.getNounProperty());
        travelerNaming.setUsedList(newUsedNounList);
        travelerNamingRepository.save(travelerNaming);

    }

    @Override
    public RandomNamingReturnDto randomNaming() {

        // 명사 형용사 기준으로 리스트를 나눔
        List<TravelerNaming> adjProperties = new ArrayList<>();
        List<TravelerNaming> nounProperties = new ArrayList<>();
        // 랜덤하게 주어질 아이디 리스트
        List<Long> adj_PK_list = new ArrayList<>();

        travelerNamingRepository.findAll().forEach(namingSource->{
            if(namingSource.getClassification() == Classification.ADJECTIVE){
                adjProperties.add(namingSource);
                adj_PK_list.add(namingSource.getId());
            }else
                nounProperties.add(namingSource);
        });

        // 랜덤성을 위해서 suffle 을 사용
        Collections.shuffle(adj_PK_list);

        while(true){
            // 형용사 리스트의 첫번째를 기준으로 루프가 돌아간다.
            Long adjTraget = Long.valueOf(adj_PK_list.get(0));

            TravelerNaming nowNamingObj= travelerNamingRepository.getOne(adjTraget);
            String usedNounList = nowNamingObj.getUsedList();
            int noun_size= nounProperties.size();


            for(int k=0;k<noun_size;k++)
                if(usedNounList.contains(nounProperties.get(k).getProperty())){
                    // 사용된 명사 목록에 포함 현재 명사가 포함되어 있다면 다음 명사로 넘어감
                    continue;
                }else{
                    // 만약 포함되어 있지 않은 경우 리턴한다.

                    RandomNamingSourceDto randomNamingSourceDto =
                            RandomNamingSourceDto.builder()
                                .adjId(nowNamingObj.getId())
                                .adjProperty(nowNamingObj.getProperty())
                                .nounProperty(nounProperties.get(k).getProperty())
                            .build();

                    RandomNamingReturnDto response =
                            RandomNamingReturnDto.builder()
                                .randomNamingSourceDto(randomNamingSourceDto)
                                .nickName(nowNamingObj.getProperty() + " " + nounProperties.get(k).getProperty())
                                .build();

                    // database에 사용했다는 것을 등록해줘야 하기 때문에
                    // 값을 리턴해줘야한다.

                    return response;
                }

            if(adj_PK_list.size() == 0)
                throw new BaseException("사용할 수 있는 조합이 더이상 없습니다.");

            // 다음 형용사 기준 조합을 생각해야함 , 사용한 형용사는 지운다.
            adj_PK_list.remove(0);
        }
    }

}
