package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductPrice;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
    @Query(value = "SELECT id, product_id, " +
            "CASE " +
            "WHEN price_type_name = 'A' THEN '성인' " +
            "WHEN price_type_name = 'B' THEN '유아' " +
            "WHEN price_type_name = 'D' THEN '장애인' " +
            "WHEN price_type_name = 'E' THEN '얼리버드' " +
            "WHEN price_type_name = 'R' THEN 'R석' " +
            "WHEN price_type_name = 'S' THEN 'S석' " +
            "WHEN price_type_name = 'V' THEN 'VIP' " +
            "WHEN price_type_name = 'Y' THEN '청소년' " +
            "END AS price_type_name, " +
            "original_price, discount_rate, create_date, modify_date, discounted_price FROM product_price WHERE product_id = :productId", nativeQuery = true)
    List<ProductPrice> findProductPrice(@Param("productId") int productId);
}
