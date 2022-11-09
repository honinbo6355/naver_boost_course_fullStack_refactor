package refactor.naver.reserve.reserveweb_refactor.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_INVALID_RESERVE_AMOUNT(HttpStatus.BAD_REQUEST, "예약 금액이 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
