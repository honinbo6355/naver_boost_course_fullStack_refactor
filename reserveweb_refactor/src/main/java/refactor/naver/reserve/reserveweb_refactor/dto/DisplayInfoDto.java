package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

@Getter
@Setter
@ToString
public class DisplayInfoDto extends SystemDate {
    private int categoryId;
    private String categoryName;
    private int displayInfoId;
    private String email;
    private String homepage;
    private String openingHours;
    private String placeLot;
    private String placeName;
    private String placeStreet;
    private String productContent;
    private String productDescription;
    private String productEvent;
    private int productId;
    private String telephone;
}
