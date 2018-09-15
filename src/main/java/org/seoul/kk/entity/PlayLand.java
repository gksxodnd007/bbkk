package org.seoul.kk.entity;

import lombok.Builder;
import lombok.Data;
import org.seoul.kk.entity.constant.Season;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "TB_PLAYLAND")
public class PlayLand {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "season", nullable = false)
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @Column(name = "content")
    private String content;

    @Column(name = "position")
    private String position;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onInitEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.likeCnt = 0L;
    }

    @PostUpdate
    public void onUpdateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

}
