package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

import javax.persistence.Embedded;

@Getter
@Setter
@ToString
public class DisplayInfoDto {
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
    private String createDate;
    private String modifyDate;
}
