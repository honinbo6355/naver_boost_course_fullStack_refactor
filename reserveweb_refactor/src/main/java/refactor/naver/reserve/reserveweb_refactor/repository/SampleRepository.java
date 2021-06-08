package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;

import javax.persistence.EntityManager;

@Repository
public class SampleRepository {
    private final EntityManager em;

    public SampleRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Sample sample) {
        em.persist(sample);
        return sample.getId();
    }

    public Sample find(Long id) {
        return em.find(Sample.class, id);
    }
}
