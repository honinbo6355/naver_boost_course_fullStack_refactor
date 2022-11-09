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
public class CommentDto {
    private String comment;
    private int commentId;
    private CommentImageDto commentImages;
    private String reservationDate;
    private String reservationEmail;
    private int reservationInfoId;
    private String reservationName;
    private String reservationTelephone;
    private double score;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
