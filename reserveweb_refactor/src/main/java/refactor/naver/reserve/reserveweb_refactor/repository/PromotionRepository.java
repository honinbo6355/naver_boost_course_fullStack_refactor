package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.dto.PromotionDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Promotion;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    @Query(value =
            "select " +
            "new refactor.naver.reserve.reserveweb_refactor.dto.PromotionDto(a.id, a.product.id, d.saveFileName) " +
            "from Promotion a " +
            "join Product b on a.product.id = b.id " +
            "join ProductImage c on b.id = c.product.id " +
            "join FileInfo d on c.fileInfo.id = d.id " +
            "where c.type = 'th'")
    List<PromotionDto> findPromotion();
}
