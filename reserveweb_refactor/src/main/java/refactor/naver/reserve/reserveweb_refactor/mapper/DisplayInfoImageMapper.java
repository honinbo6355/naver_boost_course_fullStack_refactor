package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.DisplayInfoImageDto;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfoImage;

@Mapper(componentModel = "spring")
public interface DisplayInfoImageMapper extends EntityMapper<DisplayInfoImageDto, DisplayInfoImage> {

    @Override
    @Mapping(target = "contentType", source = "fileInfo.contentType")
    @Mapping(target = "deleteFlag", source = "fileInfo.deleteFlag")
    @Mapping(target = "displayInfoId", source = "displayInfo.id")
    @Mapping(target = "displayInfoImageId", source = "id")
    @Mapping(target = "fileId", source = "fileInfo.id")
    @Mapping(target = "fileName", source = "fileInfo.fileName")
    @Mapping(target = "saveFileName", source = "fileInfo.saveFileName")
    @Mapping(target = "createDate", source = "fileInfo.createDate")
    @Mapping(target = "modifyDate", source = "fileInfo.modifyDate")
    DisplayInfoImageDto toDto(DisplayInfoImage displayInfoImage);
}
