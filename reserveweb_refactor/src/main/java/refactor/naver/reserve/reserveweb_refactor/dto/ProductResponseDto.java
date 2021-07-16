package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.Product;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductResponseDto {
    private List<ProductDto> items;
    private int totalCount;
}
