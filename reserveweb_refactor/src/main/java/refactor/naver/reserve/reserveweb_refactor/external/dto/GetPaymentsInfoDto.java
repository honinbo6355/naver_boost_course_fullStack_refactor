package refactor.naver.reserve.reserveweb_refactor.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPaymentsInfoDto {

    @JsonProperty("buyer_email")
    private String buyerEmail;

    @JsonProperty("buyer_name")
    private String buyerName;

    @JsonProperty("buyer_tel")
    private String buyerTel;

    @JsonProperty("imp_uid")
    private String impUid;

    @JsonProperty("merchant_uid")
    private String merchantUid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("pay_method")
    private String payMethod;

    @JsonProperty("pg_provider")
    private String pgProvider;

    @JsonProperty("pg_tid")
    private String pgTid;

    @JsonProperty("pg_id")
    private String pgId;

    @JsonProperty("receipt_url")
    private String receiptUrl;

    @JsonProperty("status")
    private String status;
}
