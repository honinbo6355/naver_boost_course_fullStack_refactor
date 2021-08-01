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

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "save_file_name")
    private String saveFileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "delete_flag")
    private boolean deleteFlag;

    @OneToOne(mappedBy = "fileInfo", fetch = FetchType.LAZY)
    private ProductImage productImage;

    @OneToOne(mappedBy = "fileInfo", fetch = FetchType.LAZY)
    private DisplayInfoImage displayInfoImage;

    @Embedded
    private SystemDate systemDate;
}
