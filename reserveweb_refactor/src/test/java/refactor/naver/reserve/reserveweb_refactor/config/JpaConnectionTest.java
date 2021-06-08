package refactor.naver.reserve.reserveweb_refactor.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;
import refactor.naver.reserve.reserveweb_refactor.repository.SampleRepository;

@SpringBootTest
@Transactional
public class JpaConnectionTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void saveSample() {
        Sample sample = new Sample();
        sample.setTitle("title");
        sample.setContent("content");

        Long saveId = sampleRepository.save(sample);

        Sample findSample = sampleRepository.find(saveId);

        Assertions.assertThat(findSample.getId()).isEqualTo(saveId);
    }
}
