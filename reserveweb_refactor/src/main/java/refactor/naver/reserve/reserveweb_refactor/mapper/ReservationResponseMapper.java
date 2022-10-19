package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationInfoDto;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationInfo;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationInfoPrice;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {
        DisplayInfoMapper.class
})
public interface ReservationResponseMapper extends EntityMapper<ReservationInfoDto, ReservationInfo> {

    @Override
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "reservationInfoId", source = "id")
    @Mapping(target = "reservationTelephone", source = "reservationTel")
    @Mapping(target = "totalPrice", source = "reservationInfoPrices", qualifiedByName = "mappingTotalPrice")
    ReservationInfoDto toDto(ReservationInfo reservationInfo);

    @Named("mappingTotalPrice")
    default Integer mappingTotalPrice(Set<ReservationInfoPrice> reservationInfoPrices) {
        return reservationInfoPrices.stream()
                .mapToInt(reservationInfoPrice -> reservationInfoPrice.getProductPrice().getDiscountedPrice() * reservationInfoPrice.getCount())
                .sum();
    }
}
