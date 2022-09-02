package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationInfoResponseDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationResponseDto;
import refactor.naver.reserve.reserveweb_refactor.entity.*;
import refactor.naver.reserve.reserveweb_refactor.mapper.*;
import refactor.naver.reserve.reserveweb_refactor.repository.*;
import refactor.naver.reserve.reserveweb_refactor.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ReservationInfoRepository reservationInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;
    private final UserRepository userRepository;
    private final DisplayInfoMapper displayInfoMapper;
    private final ProductImageMapper productImageMapper;
    private final ProductPriceMapper productPriceMapper;
    private final ReservationRequestMapper reservationRequestMapper;
    private final ReservationResponseMapper reservationResponseMapper;

    public ReservationServiceImpl(DisplayInfoRepository displayInfoRepository,
                                  ProductImageRepository productImageRepository,
                                  ProductPriceRepository productPriceRepository,
                                  ReservationInfoRepository reservationInfoRepository,
                                  ReservationInfoPriceRepository reservationInfoPriceRepository,
                                  UserRepository userRepository,
                                  DisplayInfoMapper displayInfoMapper,
                                  ProductImageMapper productImageMapper,
                                  ProductPriceMapper productPriceMapper,
                                  ReservationRequestMapper reservationRequestMapper,
                                  ReservationResponseMapper reservationResponseMapper) {
        this.displayInfoRepository = displayInfoRepository;
        this.productImageRepository = productImageRepository;
        this.productPriceRepository = productPriceRepository;
        this.reservationInfoRepository = reservationInfoRepository;
        this.reservationInfoPriceRepository = reservationInfoPriceRepository;
        this.userRepository = userRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageMapper = productImageMapper;
        this.productPriceMapper = productPriceMapper;
        this.reservationRequestMapper = reservationRequestMapper;
        this.reservationResponseMapper = reservationResponseMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public void createReservation(String email, ReservationRequestDto reservationRequestDto) throws Exception {
        ReservationInfo reservationInfo = reservationRequestMapper.toEntity(reservationRequestDto);
        User user = userRepository.findOneWithUserAuthoritiesByEmail(email).orElseThrow(NullPointerException::new);

        reservationRequestDto.getPrices()
                .stream()
                .forEach(priceDto -> {
                    ProductPrice productPrice = productPriceRepository.findById(priceDto.getProductPriceId()).orElseThrow(NullPointerException::new);

                    reservationInfo.addPrice(ReservationInfoPrice
                            .builder()
                            .reservationInfo(reservationInfo)
                            .productPrice(productPrice)
                            .count(priceDto.getCount())
                            .build()
                    );
                });
        reservationInfo.setUser(user);
        reservationInfo.setStatus(ReservationStatus.CONFIRMED);

        reservationInfoRepository.save(reservationInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationInfoResponseDto getReservationInfo(String email) {
        User user = userRepository.findOneWithUserAuthoritiesByEmail(email).orElseThrow(NullPointerException::new);
        List<ReservationInfo> reservationInfos = reservationInfoRepository.findByUserId(user.getId());

        ReservationInfoResponseDto response = ReservationInfoResponseDto.builder()
                .reservations(reservationInfos.stream()
                        .map(reservationResponseMapper::toDto)
                        .collect(Collectors.toList()))
                .size(reservationInfos.size())
                .build();

        return response;
    }
}
