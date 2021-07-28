package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.DisplayInfoDto;
import refactor.naver.reserve.reserveweb_refactor.dto.DisplayInfoResponseDto;
import refactor.naver.reserve.reserveweb_refactor.dto.MoreViewRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductResponseDto;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfo;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductImage;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoMapper;
import refactor.naver.reserve.reserveweb_refactor.repository.CustomQuerydslRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.DisplayInfoRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.ProductImageRepository;
import refactor.naver.reserve.reserveweb_refactor.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final CustomQuerydslRepository customQuerydslRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final DisplayInfoMapper displayInfoMapper;
    private final ProductImageRepository productImageRepository;

    public ProductServiceImpl(CustomQuerydslRepository customQuerydslRepository, DisplayInfoRepository displayInfoRepository, DisplayInfoMapper displayInfoMapper, ProductImageRepository productImageRepository) {
        this.customQuerydslRepository = customQuerydslRepository;
        this.displayInfoRepository = displayInfoRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public ProductResponseDto getProduct(int categoryId, MoreViewRequestDto moreViewRequestDto) throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setItems(customQuerydslRepository.findProduct(categoryId, moreViewRequestDto));
        productResponseDto.setTotalCount(customQuerydslRepository.findProductCount(categoryId));

        return productResponseDto;
    }

    @Override
    public DisplayInfoResponseDto getProductDetail(int displayInfoId) {
        DisplayInfoResponseDto displayInfoResponseDto = new DisplayInfoResponseDto();
        DisplayInfo displayInfo = displayInfoRepository.findDisplayInfo(displayInfoId);
        DisplayInfoDto displayInfoDto = displayInfoMapper.toDto(displayInfo);
        int productId = displayInfoDto.getProductId();

        ProductImage productImage = productImageRepository.findProductImages(productId);

        displayInfoResponseDto.setDisplayInfo(displayInfoDto);
        return displayInfoResponseDto;
    }
}
