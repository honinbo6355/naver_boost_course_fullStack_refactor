package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.dto.CategoryDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value =
            "select " +
            "new refactor.naver.reserve.reserveweb_refactor.dto.CategoryDto(a.id, a.name, count(a)) " +
            "from Category a " +
            "join Product b on a.id = b.category.id " +
            "left join DisplayInfo c " +
            "on b.id = c.product.id " +
            "group by a.id")
    List<CategoryDto> findCategory();
}
