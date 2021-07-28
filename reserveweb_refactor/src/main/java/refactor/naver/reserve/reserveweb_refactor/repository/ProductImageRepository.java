package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import refactor.naver.reserve.reserveweb_refactor.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query(value =
            "select distinct a " +
            "from ProductImage a " +
            "join fetch a.fileInfo " +
            "where a.type in ('ma', 'et') and a.product.id = :productId " +
            "order by a.type desc")
    ProductImage findProductImages(@Param("productId") int productId);
}
