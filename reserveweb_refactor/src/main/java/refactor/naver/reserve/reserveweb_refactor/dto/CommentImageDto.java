package refactor.naver.reserve.reserveweb_refactor.dto;

import refactor.naver.reserve.reserveweb_refactor.entity.SystemDate;

public class CommentImageDto extends SystemDate {
    private String contentType;
    private boolean deleteFlag;
    private int fileId;
    private String fileName;
    private int imageId;
    private int reservationInfoId;
    private int reservationUserCommentId;
    private String saveFileName;
}
