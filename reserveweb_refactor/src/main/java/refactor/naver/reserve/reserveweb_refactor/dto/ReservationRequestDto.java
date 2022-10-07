package refactor.naver.reserve.reserveweb_refactor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationRequestDto {
    private ReservationRequestDto.ReserveInfo reserveInfo;
    private ReservationRequestDto.OrdersInfo ordersInfo;

    @Getter
    @Setter
    public static class ReserveInfo {
        private int displayInfoId;
        private List<ReservationInfoPriceDto> prices;
        private int productId;
        private String reservationName;
        private String reservationTelephone;
        private String reservationEmail;
        private String reservationYearMonthDay;
    }

    @Getter
    @Setter
    public static class OrdersInfo {
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

        @JsonProperty("paid_amount")
        private Long paidAmount;

        @JsonProperty("pay_method")
        private String payMethod;

        @JsonProperty("pg_provider")
        private String pgProvider;

        @JsonProperty("pg_tid")
        private String pgTid;

        @JsonProperty("pg_type")
        private String pgType;

        @JsonProperty("receipt_url")
        private String receiptUrl;

        @JsonProperty("status")
        private String status;
    }
}
