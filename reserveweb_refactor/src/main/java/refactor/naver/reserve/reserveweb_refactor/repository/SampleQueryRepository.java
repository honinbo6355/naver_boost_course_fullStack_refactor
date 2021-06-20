package refactor.naver.reserve.reserveweb_refactor.repository;

import static refactor.naver.reserve.reserveweb_refactor.entity.QSample.sample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;

@Repository
public class SampleQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public SampleQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Sample findByTitle(String title) {
        return jpaQueryFactory.selectFrom(sample)
                .where(sample.title.eq(title))
                .fetchFirst();
    }
}
