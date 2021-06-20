package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value =
            "select a.id, a.name, count(*) as count " +
            "from category a " +
            "inner join product b on a.id = b.category_id " +
            "right outer join display_info c " +
            "on b.id = c.product_id " +
            "group by a.id", nativeQuery = true)
    List<Category> findCategory();
}
