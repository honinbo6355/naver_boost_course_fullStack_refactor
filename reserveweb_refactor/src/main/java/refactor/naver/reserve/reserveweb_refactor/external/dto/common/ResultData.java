package refactor.naver.reserve.reserveweb_refactor.external.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<T> {
    private int code;
    private String message;
    private T response;
}
