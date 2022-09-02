package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationInfoPrice;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationInfoPriceDto;

@Mapper(componentModel = "spring")
public interface ReservationInfoPriceMapper extends EntityMapper<ReservationInfoPriceDto, ReservationInfoPrice> {

    @Mapping(target = "productPrice.id", source = "productPriceId")
    @Mapping(target = "count", source = "count")
    ReservationInfoPrice toEntity(ReservationInfoPriceDto reservationInfoPriceDto);
}
