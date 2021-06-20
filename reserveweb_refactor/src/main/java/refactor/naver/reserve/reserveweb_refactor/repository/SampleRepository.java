package refactor.naver.reserve.reserveweb_refactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactor.naver.reserve.reserveweb_refactor.entity.Sample;

import java.util.Optional;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

}
