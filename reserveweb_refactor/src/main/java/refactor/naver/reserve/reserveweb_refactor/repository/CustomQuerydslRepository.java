package refactor.naver.reserve.reserveweb_refactor.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.dto.CategoryDto;
import refactor.naver.reserve.reserveweb_refactor.dto.MoreViewRequestDto;
import refactor.naver.reserve.reserveweb_refactor.dto.ProductDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Product;

import static refactor.naver.reserve.reserveweb_refactor.entity.QCategory.category;
import static refactor.naver.reserve.reserveweb_refactor.entity.QProduct.product;
import static refactor.naver.reserve.reserveweb_refactor.entity.QDisplayInfo.displayInfo;
import static refactor.naver.reserve.reserveweb_refactor.entity.QProductImage.productImage;
import static refactor.naver.reserve.reserveweb_refactor.entity.QFileInfo.fileInfo;

import java.util.List;

@Repository
public class CustomQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CustomQuerydslRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<CategoryDto> findCategory() {
//        return jpaQueryFactory
//                .select(category.id, category.name, category.count())
//                .from(category)
//                .innerJoin(product)
//                .on(category.eq(product.category))
//                .rightJoin(displayInfo)
//                .on(product.eq(displayInfo.product))
//                .groupBy(category.id)
//                .fetch();

        return jpaQueryFactory
                .select(Projections.constructor(CategoryDto.class,
                        category.id,
                        category.name,
                        category.count()))
                .from(category)
                .innerJoin(product)
                .on(category.id.eq(product.category.id))
                .rightJoin(displayInfo)
                .on(product.id.eq(displayInfo.product.id))
                .groupBy(category.id)
                .fetch();
    }

    public List<ProductDto> findProduct(int categoryId, MoreViewRequestDto moreViewRequestDto) {
        JPAQuery query = jpaQueryFactory
                .select(Projections.constructor(ProductDto.class,
                        product.id,
                        product.description,
                        product.content,
                        displayInfo.id,
                        displayInfo.placeName,
                        fileInfo.saveFileName))
                .from(product)
                .leftJoin(displayInfo)
                .on(product.id.eq(displayInfo.product.id))
                .leftJoin(productImage)
                .on(product.id.eq(productImage.product.id))
                .innerJoin(fileInfo)
                .on(productImage.fileInfo.id.eq(fileInfo.id))
                .where(productImage.type.eq("th"));

        if (categoryId != 0) {
            query.where(product.category.id.eq(categoryId));
        }

        query.offset(moreViewRequestDto.getStartViewCount()).limit(moreViewRequestDto.getEndViewCount());

        return query.fetch();
    }

    public int findProductCount(int categoryId) {
        JPAQuery query = jpaQueryFactory
                .selectFrom(product);


        if (categoryId != 0) {
            query.where(product.category.id.eq(categoryId));
        }

        return (int) query.fetchCount();
    }
}
