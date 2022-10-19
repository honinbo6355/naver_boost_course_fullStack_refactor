package refactor.naver.reserve.reserveweb_refactor.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("import-api")
@Getter
@Setter
public class ImportProperties {
    private String impKey;
    private String impSecret;
    private String baseUrl;
    private String kakaopayCid;
    private Api api;

    @Getter
    @Setter
    public static class Api {
        private String authenticate;
        private String getPaymentsInfo;
    }
}
