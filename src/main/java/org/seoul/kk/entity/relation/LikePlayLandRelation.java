package org.seoul.kk.entity.relation;

import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LIKE_PLAYLAND_RELATION")
public class LikePlayLandRelation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playland_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private PlayLand playLandId;

    @ManyToOne
    @JoinColumn(name = "traveler_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Traveler travelerId;

    @Column(name = "like_at")
    private LocalDateTime likeAt;

    @PrePersist
    public void onInitEntity() {
        this.likeAt = LocalDateTime.now();
    }

}
