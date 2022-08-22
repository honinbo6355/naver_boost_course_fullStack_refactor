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
public class CommentImageDto {
    private String contentType;
    private boolean deleteFlag;
    private int fileId;
    private String fileName;
    private int imageId;
    private int reservationInfoId;
    private int reservationUserCommentId;
    private String saveFileName;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
