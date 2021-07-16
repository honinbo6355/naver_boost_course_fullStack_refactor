package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MoreViewRequestDto {
    private int start;
    private int defaultViewCount = 4;

    public MoreViewRequestDto(int start) {
        this.start = start;
    }

    public int getStartViewCount() {
        return start * defaultViewCount;
    }

    public int getEndViewCount() {
        return defaultViewCount;
    }
}
