package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class SystemDate {
    @Column(name = "create_date")
    private String createDate;

    @Column(name = "modify_date")
    private String modifyDate;
}
