package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.Promotion;

import java.util.List;

@Getter
@Setter
@ToString
public class PromotionResponseDto {
    private List<Promotion> items;
}
