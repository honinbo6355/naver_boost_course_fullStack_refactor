package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.CategoryResponseDto;
import refactor.naver.reserve.reserveweb_refactor.repository.CategoryRepository;
import refactor.naver.reserve.reserveweb_refactor.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto findCategory() throws Exception {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setItems(categoryRepository.findCategory());
        
        return categoryResponseDto;
    }
}
