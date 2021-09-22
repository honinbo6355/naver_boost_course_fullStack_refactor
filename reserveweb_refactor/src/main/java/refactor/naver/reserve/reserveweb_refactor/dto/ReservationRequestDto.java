package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationPrice;

import java.util.List;

@Getter
@Setter
@ToString
public class ReservationRequestDto {
    private int displayInfoId;
    private List<ReservationPrice> prices;
    private int productId;
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private String reservationYearMonthDay;
}
