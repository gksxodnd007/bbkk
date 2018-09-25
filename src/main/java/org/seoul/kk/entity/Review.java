package org.seoul.kk.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "play_land_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private  PlayLand playLand;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "review_at")
    private LocalDateTime reviewAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onInitEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.reviewAt = now;
        this.updatedAt = now;
    }

    @PostUpdate
    public void onUpdateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

}
