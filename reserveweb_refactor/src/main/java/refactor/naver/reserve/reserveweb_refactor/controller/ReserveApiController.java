package refactor.naver.reserve.reserveweb_refactor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import refactor.naver.reserve.reserveweb_refactor.dto.CategoryResponseDto;
import refactor.naver.reserve.reserveweb_refactor.dto.PromotionResponseDto;
import refactor.naver.reserve.reserveweb_refactor.service.CategoryService;
import refactor.naver.reserve.reserveweb_refactor.service.PromotionService;

@RestController
@RequestMapping(path = "/api")
public class ReserveApiController {

    private CategoryService categoryService;
    private PromotionService promotionService;

    public ReserveApiController(CategoryService categoryService, PromotionService promotionService) {
        this.categoryService = categoryService;
        this.promotionService = promotionService;
    }

    @GetMapping("categories")
    public ResponseEntity<CategoryResponseDto> getCategories() {
        ResponseEntity<CategoryResponseDto> response = null;

        try {
            response = new ResponseEntity<>(categoryService.findCategory(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("promotions")
    public ResponseEntity<PromotionResponseDto> getPromotions() {
        ResponseEntity<PromotionResponseDto> response = null;
        try {
            response = new ResponseEntity<>(promotionService.getPromotion(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
