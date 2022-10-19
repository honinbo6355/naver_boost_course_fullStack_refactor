package refactor.naver.reserve.reserveweb_refactor.external;

import com.google.common.net.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import refactor.naver.reserve.reserveweb_refactor.external.dto.AuthenticateDto;
import refactor.naver.reserve.reserveweb_refactor.external.dto.GetPaymentsInfoDto;
import refactor.naver.reserve.reserveweb_refactor.external.dto.common.ResultData;

@Component
@RequiredArgsConstructor
public class ImportWebClient {

    private final WebClient webClient;
    private final ImportProperties importProperties;

    @Cacheable(value = "myCacheName", key = "#root.methodName")
    public ResultData<AuthenticateDto> getAccessToken() {
        return webClient
                .post()
                .uri(importProperties.getApi().getAuthenticate())
                .body(BodyInserters.fromFormData("imp_key", importProperties.getImpKey())
                        .with("imp_secret", importProperties.getImpSecret()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultData<AuthenticateDto>>() {})
                .block();
    }

    public ResultData<GetPaymentsInfoDto> getPaymentsInfo(final String impUid) {
        ResultData<AuthenticateDto> authenticateDto = this.getAccessToken();

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(importProperties.getApi().getGetPaymentsInfo()).build(impUid))
                .header("Authorization", "Bearer " + authenticateDto.getResponse().getAccessToken())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultData<GetPaymentsInfoDto>>() {})
                .block();
    }
}
