package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationInfo;

@Mapper(componentModel = "spring", uses = {
        ReservationInfoPriceMapper.class
})
public interface ReservationInfoMapper extends EntityMapper<ReservationRequestDto, ReservationInfo> {

    @Override
    @Mapping(target = "displayInfo.id", source = "displayInfoId")
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "reservationInfoPrices", source = "prices")
    @Mapping(target = "reservationTel", source = "reservationTelephone")
    @Mapping(target = "reservationDate", source = "reservationYearMonthDay")
    ReservationInfo toEntity(ReservationRequestDto reservationRequestDto);
}
