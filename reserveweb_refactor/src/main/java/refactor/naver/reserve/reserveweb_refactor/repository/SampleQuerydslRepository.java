package refactor.naver.reserve.reserveweb_refactor.repository;

import static refactor.naver.reserve.reserveweb_refactor.entity.QSample.sample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;

@Repository
public class SampleQuerydslRepository extends QuerydslRepositorySupport {
    public SampleQuerydslRepository() {
        super(Sample.class);
    }

    public Sample findByTitle(String title) {
        return
                from(sample)
                .where(sample.title.eq(title))
                .fetchFirst();
    }
}
