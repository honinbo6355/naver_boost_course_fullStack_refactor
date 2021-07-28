package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

import javax.persistence.Embedded;

@Getter
@Setter
@ToString
public class DisplayInfoImageDto {
    private String contentType;
    private boolean deleteFlag;
    private int displayInfoId;
    private int displayInfoImageId;
    private int fileId;
    private String fileName;
    private String saveFileName;

    @Embedded
    private SystemDate systemDate;
}
