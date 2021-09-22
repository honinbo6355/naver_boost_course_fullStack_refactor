package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationPrice {
    private int reservationInfoPriceId; // 예매 가격 id
    private int reservationInfoId; // 예매 정보 id
    private int productPriceId; // 상품 가격 id
    private int count; // 예매수
}
