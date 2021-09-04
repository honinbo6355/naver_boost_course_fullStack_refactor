package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPriceDto {
    private double discountRate;
    private int price;
    private String priceTypeName;
    private int productId;
    private int productPriceId;
    private String createDate;
    private String modifyDate;
}
