package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.MoreViewRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductResponseDto;
import refactor.naver.reserve.reserveweb_refactor.repository.CustomQuerydslRepository;
import refactor.naver.reserve.reserveweb_refactor.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private CustomQuerydslRepository customQuerydslRepository;

    public ProductServiceImpl(CustomQuerydslRepository customQuerydslRepository) {
        this.customQuerydslRepository = customQuerydslRepository;
    }

    @Override
    public ProductResponseDto getProduct(int categoryId, MoreViewRequestDto moreViewRequestDto) throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setItems(customQuerydslRepository.findProduct(categoryId, moreViewRequestDto));
        productResponseDto.setTotalCount(customQuerydslRepository.findProductCount(categoryId));

        return productResponseDto;
    }
}
