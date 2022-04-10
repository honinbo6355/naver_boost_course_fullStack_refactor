package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {
    private String email;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenValidityTime;
    private Long refreshTokenValidityTime;

    @Builder
    public UserResponseDto(String email, String grantType, String accessToken, String refreshToken, Long accessTokenValidityTime, Long refreshTokenValidityTime) {
        this.email = email;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenValidityTime = accessTokenValidityTime;
        this.refreshTokenValidityTime = refreshTokenValidityTime;
    }
}
