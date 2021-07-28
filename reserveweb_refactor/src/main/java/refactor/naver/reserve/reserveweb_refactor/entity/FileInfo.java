package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file_info")
@Getter
@Setter
public class FileInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;
    private String saveFileName;
    private String contentType;
    private int deleteFlag;

    @OneToOne(mappedBy = "fileInfo")
    private ProductImage productImage;

    @Embedded
    private SystemDate systemDate;
}
