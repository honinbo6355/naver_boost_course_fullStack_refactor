package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductImageDto;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductImage;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper extends EntityMapper<ProductImageDto, ProductImage> {

    default List<ProductImageDto> map(List<ProductImage> productImages) {
        if (productImages == null) {
            return null;
        }

        List<ProductImageDto> list = new ArrayList<ProductImageDto>(productImages.size());
        for (ProductImage image : productImages) {
            list.add(productImageToProductImageDto(image));
        }

        return list;
    }

    private ProductImageDto productImageToProductImageDto(ProductImage image) {
        if (image == null) {
            return null;
        }

        ProductImageDto dto = new ProductImageDto();

        dto.setContentType(image.getFileInfo().getContentType());
        dto.setDeleteFlag(image.getFileInfo().isDeleteFlag());
        dto.setFileInfoId(image.getFileInfo().getId());
        dto.setFileName(image.getFileInfo().getFileName());
        dto.setProductId(image.getProduct().getId());
        dto.setProductImageId(image.getId());
        dto.setSaveFileName(image.getFileInfo().getSaveFileName());
        dto.setType(image.getType());
        dto.setCreateDate(image.getFileInfo().getSystemDate().getCreateDate());
        dto.setModifyDate(image.getFileInfo().getSystemDate().getModifyDate());

        return dto;
    }
}
