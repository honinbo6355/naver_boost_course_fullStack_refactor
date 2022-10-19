package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<ReservationRequestDto.OrdersInfo, Orders> {

    @Override
    Orders toEntity(ReservationRequestDto.OrdersInfo ordersInfo);
}
