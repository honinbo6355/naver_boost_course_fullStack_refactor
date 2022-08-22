package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CONFIRMED("CONFIRMED", "예약 확정"),
    COMPLETED("COMPLETED", "이용 완료"),
    CANCELED("CANCELED", "예약 취소");

    private String value;
    private String description;

    ReservationStatus(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
