package refactor.naver.reserve.reserveweb_refactor.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.CategoryDto;
import refactor.naver.reserve.reserveweb_refactor.dto.MoreViewRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;
import refactor.naver.reserve.reserveweb_refactor.repository.CustomQuerydslRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.SampleQuerydslRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.SampleRepository;

import java.util.List;

@SpringBootTest
@Transactional
public class JpaConnectionTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private SampleQuerydslRepository sampleQuerydslRepository;

    @Autowired
    private CustomQuerydslRepository customQuerydslRepository;

    @Test
    public void saveSample() {
        Sample sample = new Sample();
        sample.setTitle("title4");
        sample.setContent("content4");

        Sample savedSample = sampleRepository.save(sample);

        Sample findSample = sampleQuerydslRepository.findByTitle(savedSample.getTitle());

        Assertions.assertThat(findSample.getId()).isEqualTo(savedSample.getId());
    }

    @Test
    public void findCategories() {
        List<CategoryDto> result = customQuerydslRepository.findCategory();
        System.out.println("result : " + result);
    }

    @Test
    public void findProduct() {
        int categoryId = 3;
        MoreViewRequestDto moreViewRequestDto = new MoreViewRequestDto(1);

        List<ProductDto> result = customQuerydslRepository.findProduct(categoryId, moreViewRequestDto);
        Assertions.assertThat(result.size()).isEqualTo(4);
    }
}
