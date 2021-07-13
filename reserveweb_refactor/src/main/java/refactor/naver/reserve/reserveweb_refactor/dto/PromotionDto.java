package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PromotionDto {
    private int id;
    private int productId;
    private String productImageUrl;

    public PromotionDto(int id, int productId, String productImageUrl) {
        this.id = id;
        this.productId = productId;
        this.productImageUrl = productImageUrl;
    }
}
