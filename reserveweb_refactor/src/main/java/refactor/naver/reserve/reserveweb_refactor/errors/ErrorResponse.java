package refactor.naver.reserve.reserveweb_refactor.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ErrorResponse {
    private final String code;
    private final String message;
}
