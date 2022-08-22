package refactor.naver.reserve.reserveweb_refactor.service;

import refactor.naver.reserve.reserveweb_refactor.dto.ReservationInfoResponseDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto getReservePageInfo(int displayInfoId) throws Exception;

    void createReservation(String email, ReservationRequestDto reservationRequestDto) throws Exception;

    ReservationInfoResponseDto getReservationInfo(String email);
}
