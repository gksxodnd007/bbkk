package org.seoul.kk.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_BOOKMARK")
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "traveler_id" , foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "playland_id", foreignKey = @ForeignKey(name = "none"))
    private PlayLand playLand;

    @Column(name = "mark_at")
    private LocalDateTime markAt;

    @PrePersist
    public void onInitEntity() {
        this.markAt = LocalDateTime.now();
    }

}
