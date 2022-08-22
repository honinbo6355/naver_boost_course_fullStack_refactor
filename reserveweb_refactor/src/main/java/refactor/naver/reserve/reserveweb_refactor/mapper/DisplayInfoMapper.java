package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.DisplayInfoDto;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfo;

@Mapper(componentModel = "spring")
public interface DisplayInfoMapper extends EntityMapper<DisplayInfoDto, DisplayInfo> {

    @Override
    @Mapping(target = "displayInfoId", source = "id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productContent", source = "product.content")
    @Mapping(target = "productDescription", source = "product.description")
    @Mapping(target = "productEvent", source = "product.event")
    @Mapping(target = "categoryId", source = "product.category.id")
    @Mapping(target = "categoryName", source = "product.category.name")
    @Mapping(target = "createDate", source = "createDate")
    @Mapping(target = "modifyDate", source = "modifyDate")
    DisplayInfoDto toDto(DisplayInfo displayInfo);
}
