package org.seoul.kk.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_REVIEW")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "play_land_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private  PlayLand playLand;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "like_at")
    private LocalDateTime likeAt;

    @PrePersist
    public void onInitEntity() {
        this.likeAt = LocalDateTime.now();
    }

}
