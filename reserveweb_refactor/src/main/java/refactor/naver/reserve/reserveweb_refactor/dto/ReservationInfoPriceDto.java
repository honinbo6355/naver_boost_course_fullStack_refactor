package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationInfoPriceDto {
    private int productPriceId; // 상품 가격 id
    private int count; // 예매수
}
