package refactor.naver.reserve.reserveweb_refactor.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
}
