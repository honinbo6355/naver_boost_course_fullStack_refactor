package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.DisplayInfoImage;

@Repository
public interface DisplayInfoImageRepository extends JpaRepository<DisplayInfoImage, Integer> {

    @Query(value =
            "select a " +
            "from DisplayInfoImage a " +
            "join fetch a.fileInfo " +
            "where a.id = :displayInfoId")
    DisplayInfoImage findDisplayInfoImage(@Param("displayInfoId") int displayInfoId);
}
