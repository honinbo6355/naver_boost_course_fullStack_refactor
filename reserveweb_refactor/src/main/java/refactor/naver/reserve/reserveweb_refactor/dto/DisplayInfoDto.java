package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DisplayInfoDto {
    private Integer categoryId;
    private String categoryName;
    private Integer displayInfoId;
    private String email;
    private String homepage;
    private String openingHours;
    private String placeLot;
    private String placeName;
    private String placeStreet;
    private String productContent;
    private String productDescription;
    private String productEvent;
    private Integer productId;
    private String telephone;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
