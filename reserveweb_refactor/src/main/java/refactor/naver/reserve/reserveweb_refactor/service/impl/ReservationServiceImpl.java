package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationResponseDto;
import refactor.naver.reserve.reserveweb_refactor.entity.*;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ProductImageMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ProductPriceMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ReservationInfoMapper;
import refactor.naver.reserve.reserveweb_refactor.repository.*;
import refactor.naver.reserve.reserveweb_refactor.service.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ReservationInfoRepository reservationInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;
    private final DisplayInfoMapper displayInfoMapper;
    private final ProductImageMapper productImageMapper;
    private final ProductPriceMapper productPriceMapper;
    private final ReservationInfoMapper reservationInfoMapper;

    public ReservationServiceImpl(DisplayInfoRepository displayInfoRepository,
                                  ProductImageRepository productImageRepository,
                                  ProductPriceRepository productPriceRepository,
                                  ReservationInfoRepository reservationInfoRepository,
                                  ReservationInfoPriceRepository reservationInfoPriceRepository,
                                  DisplayInfoMapper displayInfoMapper,
                                  ProductImageMapper productImageMapper,
                                  ProductPriceMapper productPriceMapper,
                                  ReservationInfoMapper reservationInfoMapper) {
        this.displayInfoRepository = displayInfoRepository;
        this.productImageRepository = productImageRepository;
        this.productPriceRepository = productPriceRepository;
        this.reservationInfoRepository = reservationInfoRepository;
        this.reservationInfoPriceRepository = reservationInfoPriceRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageMapper = productImageMapper;
        this.productPriceMapper = productPriceMapper;
        this.reservationInfoMapper = reservationInfoMapper;
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

    @Override
    @Transactional
    public void createReservation(ReservationRequestDto reservationRequestDto) throws Exception {
        ReservationInfo reservationInfo = reservationInfoMapper.toEntity(reservationRequestDto);

        reservationInfoRepository.save(reservationInfo);

        reservationInfo.getReservationInfoPrices().stream().map(reservationInfoPrice -> {
            reservationInfoPrice.getReservationInfo().setId(reservationInfo.getId());
            return reservationInfoPrice;
        }).forEach(reservationInfoPriceRepository::save);
    }
}
