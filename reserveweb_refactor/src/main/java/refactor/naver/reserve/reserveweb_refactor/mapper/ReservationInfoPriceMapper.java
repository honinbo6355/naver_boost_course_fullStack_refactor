package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationInfoPrice;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationPrice;

@Mapper(componentModel = "spring")
public interface ReservationInfoPriceMapper extends EntityMapper<ReservationPrice, ReservationInfoPrice> {

    @Mapping(target = "id", source = "reservationInfoPriceId")
    @Mapping(target = "reservationInfo.id", source = "reservationInfoId")
    @Mapping(target = "productPrice.id", source = "productPriceId")
    @Mapping(target = "count", source = "count")
    ReservationInfoPrice toEntity(ReservationPrice reservationPrice);
}
