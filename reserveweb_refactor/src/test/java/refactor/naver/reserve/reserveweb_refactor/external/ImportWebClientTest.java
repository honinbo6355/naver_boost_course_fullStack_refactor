package refactor.naver.reserve.reserveweb_refactor.external;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import refactor.naver.reserve.reserveweb_refactor.external.dto.AuthenticateDto;
import refactor.naver.reserve.reserveweb_refactor.external.dto.GetPaymentsInfoDto;
import refactor.naver.reserve.reserveweb_refactor.external.dto.common.ResultData;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImportWebClientTest {

    @Autowired
    private ImportWebClient importWebClient;

    @Test
    public void 인증정보_조회() {

        ResultData<AuthenticateDto> response = importWebClient.getAccessToken();

        Assertions.assertThat(response.getCode()).isEqualTo(0);
        Assertions.assertThat(response.getMessage()).isNull();
        Assertions.assertThat(response.getResponse()).isNotNull();
    }

    @Test
    public void 결제내역_조회() {
        final String impUid = "imp_579741369222";

        ResultData<GetPaymentsInfoDto> response = importWebClient.getPaymentsInfo(impUid);

        Assertions.assertThat(response.getCode()).isEqualTo(0);
        Assertions.assertThat(response.getMessage()).isNull();
        Assertions.assertThat(response.getResponse()).isNotNull();
    }
}
