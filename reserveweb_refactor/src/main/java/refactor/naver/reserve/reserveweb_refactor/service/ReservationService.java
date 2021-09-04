package refactor.naver.reserve.reserveweb_refactor.service;

import refactor.naver.reserve.reserveweb_refactor.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto getReservePageInfo(int displayInfoId) throws Exception;
}
