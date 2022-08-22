package refactor.naver.reserve.reserveweb_refactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReservewebRefactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservewebRefactorApplication.class, args);
	}
}
