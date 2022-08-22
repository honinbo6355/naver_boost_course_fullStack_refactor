package refactor.naver.reserve.reserveweb_refactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductPriceDto;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductPrice;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper extends EntityMapper<ProductPriceDto, ProductPrice> {

    @Override
    @Mapping(target = "discountRate", source = "discountRate")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "priceTypeName", source = "priceTypeName")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productPriceId", source = "id")
    @Mapping(target = "createDate", source = "createDate")
    @Mapping(target = "modifyDate", source = "modifyDate")
    ProductPriceDto toDto(ProductPrice productPrice);

    default List<ProductPriceDto> toDto(List<ProductPrice> productPrices) {
        return productPrices.stream().map(this::toDto).collect(Collectors.toList());
    }
}
