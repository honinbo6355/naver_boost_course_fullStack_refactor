package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReservationRequestDto {
    private int displayInfoId;
    private List<ReservationInfoPriceDto> prices;
    private int productId;
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private String reservationYearMonthDay;
}
