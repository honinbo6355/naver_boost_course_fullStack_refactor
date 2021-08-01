package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.*;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfo;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfoImage;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductImage;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoImageMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ProductImageMapper;
import refactor.naver.reserve.reserveweb_refactor.repository.CustomQuerydslRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.DisplayInfoImageRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.DisplayInfoRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.ProductImageRepository;
import refactor.naver.reserve.reserveweb_refactor.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final CustomQuerydslRepository customQuerydslRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductImageRepository productImageRepository;
    private final DisplayInfoImageRepository displayInfoImageRepository;
    private final DisplayInfoMapper displayInfoMapper;
    private final ProductImageMapper productImageMapper;
    private final DisplayInfoImageMapper displayInfoImageMapper;

    public ProductServiceImpl(CustomQuerydslRepository customQuerydslRepository,
                              DisplayInfoRepository displayInfoRepository,
                              ProductImageRepository productImageRepository,
                              DisplayInfoImageRepository displayInfoImageRepository,
                              DisplayInfoMapper displayInfoMapper,
                              ProductImageMapper productImageMapper,
                              DisplayInfoImageMapper displayInfoImageMapper) {
        this.customQuerydslRepository = customQuerydslRepository;
        this.displayInfoRepository = displayInfoRepository;
        this.displayInfoImageRepository = displayInfoImageRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
        this.displayInfoImageMapper = displayInfoImageMapper;
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
        DisplayInfoDto displayInfoDto = displayInfoMapper.toDto(displayInfoRepository.findDisplayInfo(displayInfoId));
        int productId = displayInfoDto.getProductId();
        List<ProductImageDto> productImageDto = productImageMapper.map(productImageRepository.findProductImages(productId));
        DisplayInfoImageDto displayInfoImageDto = displayInfoImageMapper.toDto(displayInfoImageRepository.findDisplayInfoImage(displayInfoId));

        displayInfoResponseDto.setDisplayInfo(displayInfoDto);
        displayInfoResponseDto.setProductImages(productImageDto);
        displayInfoResponseDto.setDisplayInfoImage(displayInfoImageDto);

        return displayInfoResponseDto;
    }
}
