package refactor.naver.reserve.reserveweb_refactor.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Category;

import static refactor.naver.reserve.reserveweb_refactor.entity.QCategory.category;
import static refactor.naver.reserve.reserveweb_refactor.entity.QProduct.product;
import static refactor.naver.reserve.reserveweb_refactor.entity.QDisplayInfo.displayInfo;

import java.util.List;

@Repository
public class CategoryQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CategoryQuerydslRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Tuple> findCategory() {
        return jpaQueryFactory
                .select(category.id, category.name, category.count())
                .from(category)
                .innerJoin(product)
                .on(category.eq(product.category))
                .rightJoin(displayInfo)
                .on(product.eq(displayInfo.product))
                .groupBy(category.id)
                .fetch();
    }
}
