package refactor.naver.reserve.reserveweb_refactor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactor.naver.reserve.reserveweb_refactor.dto.*;
import refactor.naver.reserve.reserveweb_refactor.service.CategoryService;
import refactor.naver.reserve.reserveweb_refactor.service.ProductService;
import refactor.naver.reserve.reserveweb_refactor.service.PromotionService;
import refactor.naver.reserve.reserveweb_refactor.service.ReservationService;

@RestController
@RequestMapping(path = "/api")
public class ReserveApiController {

    private final CategoryService categoryService;
    private final PromotionService promotionService;
    private final ProductService productService;
    private final ReservationService reservationService;

    public ReserveApiController(CategoryService categoryService, PromotionService promotionService, ProductService productService, ReservationService reservationService) {
        this.categoryService = categoryService;
        this.promotionService = promotionService;
        this.productService = productService;
        this.reservationService = reservationService;
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

    @GetMapping("products")
    public ResponseEntity<ProductResponseDto> getProducts(@RequestParam int categoryId, @RequestParam(defaultValue = "0") int start) {
        ResponseEntity<ProductResponseDto> response = null;
        try {
            MoreViewRequestDto moreViewRequestDto = new MoreViewRequestDto(start);
            response = new ResponseEntity<>(productService.getProduct(categoryId, moreViewRequestDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("products/{displayInfoId}")
    public ResponseEntity<DisplayInfoResponseDto> getProductsDetail(@PathVariable("displayInfoId") int displayInfoId) {
        ResponseEntity<DisplayInfoResponseDto> response = null;
        try {
            response = new ResponseEntity<>(productService.getProductDetail(displayInfoId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("reserve/{displayInfoId}")
    public ResponseEntity<ReservationResponseDto> getReservePageInfo(@PathVariable("displayInfoId") int displayInfoId) {
        ResponseEntity<ReservationResponseDto> response = null;
        try {
            response = new ResponseEntity<>(reservationService.getReservePageInfo(displayInfoId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PostMapping("reserve")
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        ResponseEntity<String> response = null;
        try {
            reservationService.createReservation(reservationRequestDto);
            response = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
