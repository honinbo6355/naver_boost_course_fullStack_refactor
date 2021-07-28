package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfo;

@Repository
public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Integer> {
    @Query(value =
            "select distinct a " +
            "from DisplayInfo a " +
            "join fetch a.product b " +
            "where a.id = :displayInfoId")
    DisplayInfo findDisplayInfo(@Param("displayInfoId") int displayInfoId);
}
