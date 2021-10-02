package refactor.naver.reserve.reserveweb_refactor.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import refactor.naver.reserve.reserveweb_refactor.dto.*;
import refactor.naver.reserve.reserveweb_refactor.entity.User;
import refactor.naver.reserve.reserveweb_refactor.jwt.JwtFilter;
import refactor.naver.reserve.reserveweb_refactor.jwt.TokenProvider;
import refactor.naver.reserve.reserveweb_refactor.service.*;

@RestController
@RequestMapping(path = "/api")
public class ReserveApiController {

    private final CategoryService categoryService;
    private final PromotionService promotionService;
    private final ProductService productService;
    private final ReservationService reservationService;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ReserveApiController(CategoryService categoryService, PromotionService promotionService, ProductService productService, ReservationService reservationService, UserService userService,
                                TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.categoryService = categoryService;
        this.promotionService = promotionService;
        this.productService = productService;
        this.reservationService = reservationService;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
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

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        ResponseEntity<String> response = null;
        try {
            userService.signup(user);
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PostMapping("doLogin")
    public ResponseEntity<String> doLogin(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        // authenticate 메소드가 실행될때 CustomUserDetailsServiceImpl => loadUserByUsername 메소드가 내부적으로 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }
}
