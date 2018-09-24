package org.seoul.kk.repository;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.QPlayLand;
import org.seoul.kk.entity.QTraveler;
import org.seoul.kk.entity.constant.Season;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Slf4j
public class PlayLandCustomRepositoryImpl extends QuerydslRepositorySupport implements PlayLandCustomRepository {

    private static final QPlayLand playLand = QPlayLand.playLand;
    private static final QTraveler traveler = QTraveler.traveler;

    public PlayLandCustomRepositoryImpl() {
        super(PlayLand.class);
    }

    @Override
    public List<PlayLand> findPlayLandBySeasonOrderByLikeCnt(Season season) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .where(playLand.season.eq(season))
                .orderBy(playLand.likeCnt.desc());

        return query.fetch();
    }

    @Override
    public List<PlayLand> findPlayLandOrderByLikeCntLimit(long limitSize) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .orderBy(playLand.likeCnt.desc())
                .limit(limitSize);

        return query.fetch();
    }

    @Override
    public List<PlayLand> findPlayLandOrderByCreatedAtFromCursorLimit(long cursor, long size) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .orderBy(playLand.createdAt.desc())
                .offset(cursor)
                .limit(size);

        return query.fetch();
    }
}
