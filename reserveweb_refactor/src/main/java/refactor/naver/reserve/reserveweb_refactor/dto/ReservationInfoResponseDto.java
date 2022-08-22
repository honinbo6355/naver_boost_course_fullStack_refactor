package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationInfoResponseDto {
    private List<ReservationInfoDto> reservations;
    private Integer size;
}
