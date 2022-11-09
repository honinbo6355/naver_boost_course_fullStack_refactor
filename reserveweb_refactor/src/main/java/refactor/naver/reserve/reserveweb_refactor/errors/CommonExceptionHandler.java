package refactor.naver.reserve.reserveweb_refactor.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException customException) {
        ErrorCode errorCode = customException.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse
                        .builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
