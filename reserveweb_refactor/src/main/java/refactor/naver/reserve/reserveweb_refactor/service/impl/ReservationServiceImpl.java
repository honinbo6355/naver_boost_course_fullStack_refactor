package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationResponseDto;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfo;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductImage;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductPrice;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ProductImageMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ProductPriceMapper;
import refactor.naver.reserve.reserveweb_refactor.repository.DisplayInfoRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.ProductImageRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.ProductPriceRepository;
import refactor.naver.reserve.reserveweb_refactor.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductPriceRepository productPriceRepository;
    private final DisplayInfoMapper displayInfoMapper;
    private final ProductImageMapper productImageMapper;
    private final ProductPriceMapper productPriceMapper;

    public ReservationServiceImpl(DisplayInfoRepository displayInfoRepository,
                                  ProductImageRepository productImageRepository,
                                  ProductPriceRepository productPriceRepository,
                                  DisplayInfoMapper displayInfoMapper,
                                  ProductImageMapper productImageMapper,
                                  ProductPriceMapper productPriceMapper) {
        this.displayInfoRepository = displayInfoRepository;
        this.productImageRepository = productImageRepository;
        this.productPriceRepository = productPriceRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageMapper = productImageMapper;
        this.productPriceMapper = productPriceMapper;
    }

    @Override
    public ReservationResponseDto getReservePageInfo(int displayInfoId) throws Exception {
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

        DisplayInfo displayInfo = displayInfoRepository.findDisplayInfo(displayInfoId);
        int productId = displayInfo.getProduct().getId();
        List<ProductImage> productImages = productImageRepository.findProductImages(productId);
        List<ProductPrice> prices = productPriceRepository.findProductPrice(productId);

        Random random = new Random();
        int randDay = random.nextInt(5) + 1;

        String reservationDate = LocalDate.now().plusDays(randDay).toString();

        reservationResponseDto.setDisplayInfo(displayInfoMapper.toDto(displayInfo));
        reservationResponseDto.setProductImages(productImageMapper.toDto(productImages));
        reservationResponseDto.setPrices(productPriceMapper.toDto(prices));
        reservationResponseDto.setReservationDate(reservationDate);

        return reservationResponseDto;
    }
}
