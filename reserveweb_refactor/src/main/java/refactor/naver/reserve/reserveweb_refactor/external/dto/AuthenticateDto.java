package refactor.naver.reserve.reserveweb_refactor.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateDto {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("now")
    private Long now;

    @JsonProperty("expired_at")
    private Long expiredAt;
}
