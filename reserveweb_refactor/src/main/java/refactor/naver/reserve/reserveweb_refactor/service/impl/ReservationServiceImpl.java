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

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
    private final ReservationInfoMapper reservationInfoMapper;
    private final ReservationResponseMapper reservationResponseMapper;
    private final OrderMapper orderMapper;

    public ReservationServiceImpl(DisplayInfoRepository displayInfoRepository,
                                  ProductImageRepository productImageRepository,
                                  ProductPriceRepository productPriceRepository,
                                  ReservationInfoRepository reservationInfoRepository,
                                  ReservationInfoPriceRepository reservationInfoPriceRepository,
                                  UserRepository userRepository,
                                  DisplayInfoMapper displayInfoMapper,
                                  ProductImageMapper productImageMapper,
                                  ProductPriceMapper productPriceMapper,
                                  ReservationInfoMapper reservationInfoMapper,
                                  ReservationResponseMapper reservationResponseMapper,
                                  OrderMapper orderMapper) {
        this.displayInfoRepository = displayInfoRepository;
        this.productImageRepository = productImageRepository;
        this.productPriceRepository = productPriceRepository;
        this.reservationInfoRepository = reservationInfoRepository;
        this.reservationInfoPriceRepository = reservationInfoPriceRepository;
        this.userRepository = userRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageMapper = productImageMapper;
        this.productPriceMapper = productPriceMapper;
        this.reservationInfoMapper = reservationInfoMapper;
        this.reservationResponseMapper = reservationResponseMapper;
        this.orderMapper = orderMapper;
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
        String reserveDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String reserveNumber = String.valueOf(new BigInteger(
                UUID.randomUUID().toString().replace("-", ""), 16))
                .substring(0, 5);

        String reservationDate = LocalDate.now().plusDays(randDay).toString();

        reservationResponseDto.setDisplayInfo(displayInfoMapper.toDto(displayInfo));
        reservationResponseDto.setProductImages(productImageMapper.toDto(productImages));
        reservationResponseDto.setPrices(productPriceMapper.toDto(prices));
        reservationResponseDto.setReservationDate(reservationDate);
        reservationResponseDto.setReserveNumber(reserveDate+reserveNumber);

        return reservationResponseDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createReservation(String email, ReservationRequestDto reservationRequestDto) throws Exception {
        ReservationInfo reservationInfo = reservationInfoMapper.toEntity(reservationRequestDto.getReserveInfo());
        User user = userRepository.findOneWithUserAuthoritiesByEmail(email).orElseThrow(NullPointerException::new);
        Orders orders = orderMapper.toEntity(reservationRequestDto.getOrdersInfo());

        /*
            1. 인증 정보 발급
            2. 결제 정보 조회
            3. 요청값의 금액과 비교(금액 변경 체크)
            4. 동일하면 insert
         */

        reservationRequestDto.getReserveInfo().getPrices()
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
        reservationInfo.setOrders(orders);

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
