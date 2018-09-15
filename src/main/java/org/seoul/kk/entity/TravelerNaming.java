package org.seoul.kk.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_TRAVLER_NAMING")
public class TravelerNaming {
    @Id
    @GeneratedValue
    private Long id;
    // classification about string if classification is number 'one' it's property is adjective
    // and if number is 'two' property is noun
    // 1 : 형용사 2 : 명사
    @Column(name = "classification")
    private Integer classification;
    // 들어가는 값
    @Column(name = "property")
    private String property;
    // 중복확인에 필요한 리스트
    // 명사는 값을 갖지 않는다.
    @Column(name = "noun_list")
    private String usedList;

}
