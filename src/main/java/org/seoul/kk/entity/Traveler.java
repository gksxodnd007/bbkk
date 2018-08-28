package org.seoul.kk.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.seoul.kk.entity.constant.TravelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TB_TRAVELER")
public class Traveler {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "travel_property")
    private TravelProperty travelProperty;

    //TODO foreign key 제약 해제 하는 방법을 찾아봅니다.
    @OneToMany(mappedBy = "travelerId",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<BookMark> bookMarks = new ArrayList<>();

    public void addBookMark(BookMark bookMark) {
        this.bookMarks.add(bookMark);
        bookMark.setTraveler(this);
    }

    public void removeBookMark(BookMark bookMark) {
        this.bookMarks.remove(bookMark);
        bookMark.setTraveler(this);
    }

    @PrePersist
    public void onInitEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PostUpdate
    public void onUpdateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

}
