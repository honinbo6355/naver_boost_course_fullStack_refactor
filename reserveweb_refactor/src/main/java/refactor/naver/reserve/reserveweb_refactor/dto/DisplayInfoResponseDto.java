package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DisplayInfoResponseDto {
    private double averageScore;
    private CommentDto comments;
    private DisplayInfoDto displayInfo;
    private DisplayInfoImageDto displayInfoImage;
    private List<ProductImageDto> productImages;
}
