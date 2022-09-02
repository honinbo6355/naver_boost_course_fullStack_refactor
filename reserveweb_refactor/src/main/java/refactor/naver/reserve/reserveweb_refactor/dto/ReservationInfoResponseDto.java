package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationInfoResponseDto {
    private List<ReservationInfoDto> reservations;
    private Integer size;

    @Builder
    public ReservationInfoResponseDto(List<ReservationInfoDto> reservations, Integer size) {
        this.reservations = reservations;
        this.size = size;
    }
}
