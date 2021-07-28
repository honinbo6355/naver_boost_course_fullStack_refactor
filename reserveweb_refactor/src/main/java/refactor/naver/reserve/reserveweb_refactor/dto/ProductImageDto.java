package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

import javax.persistence.Embedded;

@Getter
@Setter
@ToString
public class ProductImageDto {
    private String contentType;
    private boolean deleteFlag;
    private int fileInfoId;
    private String fileName;
    private int productId;
    private int productImageId;
    private String saveFileName;
    private String type;

    @Embedded
    private SystemDate systemDate;
}
