package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value =
            "select distinct a " +
            "from Comment a " +
            "join fetch a.reservationInfo b " +
            "left join fetch a.commentImage c " +
            "left join fetch c.fileInfo d")
    List<Comment> findComments(@Param("productId") int productId);

    @Query(value =
            "select ifnull(round(avg(score), 1), 0.0) " +
            "from reservation_user_comment " +
            "where product_id = :productId", nativeQuery = true)
    Double findAverageScore(@Param("productId") int productId);
}
