package refactor.naver.reserve.reserveweb_refactor.service;

import refactor.naver.reserve.reserveweb_refactor.dto.MoreViewRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto getProduct(int categoryId, MoreViewRequestDto moreViewRequestDto) throws Exception;
}
