package refactor.naver.reserve.reserveweb_refactor.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;
import refactor.naver.reserve.reserveweb_refactor.repository.SampleQueryRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.SampleRepository;

@SpringBootTest
@Transactional
public class JpaConnectionTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private SampleQueryRepository sampleQueryRepository;

    @Test
    public void saveSample() {
        Sample sample = new Sample();
        sample.setTitle("title4");
        sample.setContent("content4");

        Sample savedSample = sampleRepository.save(sample);

        Sample findSample = sampleQueryRepository.findByTitle(savedSample.getTitle());

        Assertions.assertThat(findSample.getId()).isEqualTo(savedSample.getId());
    }
}
