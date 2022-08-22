package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.CommentImageDto;
import refactor.naver.reserve.reserveweb_refactor.entity.CommentImage;

@Mapper(componentModel = "spring")
public interface CommentImageMapper extends EntityMapper<CommentImageDto, CommentImage> {

    @Override
    @Mapping(target = "contentType", source = "fileInfo.contentType")
    @Mapping(target = "deleteFlag", source = "fileInfo.deleteFlag")
    @Mapping(target = "fileId", source = "fileInfo.id")
    @Mapping(target = "fileName", source = "fileInfo.fileName")
    @Mapping(target = "imageId", source = "id")
    @Mapping(target = "reservationInfoId", source = "reservationInfo.id")
    @Mapping(target = "reservationUserCommentId", source = "comment.id")
    @Mapping(target = "saveFileName", source = "fileInfo.saveFileName")
    @Mapping(target = "createDate", source = "fileInfo.createDate")
    @Mapping(target = "modifyDate", source = "fileInfo.modifyDate")
    CommentImageDto toDto(CommentImage commentImage);
}
