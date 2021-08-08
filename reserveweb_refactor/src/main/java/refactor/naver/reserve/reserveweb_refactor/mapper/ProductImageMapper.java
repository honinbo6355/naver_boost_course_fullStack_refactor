package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductImageDto;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductImageMapper extends EntityMapper<ProductImageDto, ProductImage> {

    @Override
    @Mapping(target = "contentType", source = "fileInfo.contentType")
    @Mapping(target = "deleteFlag", source = "fileInfo.deleteFlag")
    @Mapping(target = "fileInfoId", source = "fileInfo.id")
    @Mapping(target = "fileName", source = "fileInfo.fileName")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productImageId", source = "id")
    @Mapping(target = "saveFileName", source = "fileInfo.saveFileName")
    @Mapping(target = "createDate", source = "fileInfo.systemDate.createDate")
    @Mapping(target = "modifyDate", source = "fileInfo.systemDate.modifyDate")
    ProductImageDto toDto(ProductImage productImage);

    default List<ProductImageDto> toDto(List<ProductImage> productImages) {
        return productImages.stream().map(this::toDto).collect(Collectors.toList());
    }
}
