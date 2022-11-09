package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReservationResponseDto {
    private DisplayInfoDto displayInfo;
    private List<ProductImageDto> productImages;
    private List<ProductPriceDto> prices;
    private String reservationDate;
    private String reserveNumber;
    private String kakaopayCid;
    private String storeCode;
}
