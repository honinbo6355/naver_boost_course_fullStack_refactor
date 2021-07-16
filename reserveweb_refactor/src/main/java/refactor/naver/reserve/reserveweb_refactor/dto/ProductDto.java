package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private int id;
    private String description;
    private String content;
    private int displayInfoId;
    private String placeName;
    private String imageUrl;

    public ProductDto(int id, String description, String content, int displayInfoId, String placeName, String imageUrl) {
        this.id = id;
        this.description = description;
        this.content = content;
        this.displayInfoId = displayInfoId;
        this.placeName = placeName;
        this.imageUrl = imageUrl;
    }
}
