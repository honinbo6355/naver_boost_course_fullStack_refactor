package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.ReservationInfoPrice;

@Repository
public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPrice, Integer> {

}
