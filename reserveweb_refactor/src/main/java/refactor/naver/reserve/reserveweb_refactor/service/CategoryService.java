package refactor.naver.reserve.reserveweb_refactor.service;

import refactor.naver.reserve.reserveweb_refactor.dto.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto findCategory() throws Exception;
}
