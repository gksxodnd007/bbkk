package org.seoul.kk.entity;

import lombok.Builder;
import lombok.Data;
import org.seoul.kk.entity.constant.Classification;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "TB_TRAVLER_NAMING")
public class TravelerNaming {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "classification")
    private Classification classification;
    // 들어가는 값
    @Column(name = "property")
    private String property;
    // 중복확인에 필요한 리스트
    // 명사는 값을 갖지 않는다.
    @Column(name = "noun_list")
    private String usedList;

}
