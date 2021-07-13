package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDto {
    private int id;
    private String name;
    private long count;

    public CategoryDto(int id, String name, long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
