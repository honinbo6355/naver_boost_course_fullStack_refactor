package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationInfoDto {
    private ReservationStatus status;
    private LocalDateTime createDate;
    private DisplayInfoDto displayInfo;
    private Integer displayInfoId;
    private LocalDateTime modifyDate;
    private Integer productId;
    private String reservationDate;
    private String reservationEmail;
    private Integer reservationInfoId;
    private String reservationName;
    private String reservationTelephone;
    private Long totalPrice;
}
