package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

@Getter
@Setter
@ToString
public class CommentDto extends SystemDate {
    private String comment;
    private int commentId;
    private CommentImageDto commentImages;
    private int productId;
    private String reservationDate;
    private String reservationEmail;
    private int reservationInfoId;
    private String reservationName;
    private String reservationTelephone;
    private double score;
}
