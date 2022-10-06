package site.metacoding.test.domain.img;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgDto {
    private Integer id;
    private String title;
    private String imgName;
    private String content;
    private Timestamp createdAt;

    public Img toEntity(String imgName){
        Img img = new Img(this.title, imgName, this.content);
        return img;
    }
}
