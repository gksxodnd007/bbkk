package org.seoul.kk.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

}
