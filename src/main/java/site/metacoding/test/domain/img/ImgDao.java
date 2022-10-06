package site.metacoding.test.domain.img;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImgDao {
	public void save(String imgName);
	public void update();
	public Img findById(Integer id);
	void saveUploadFiles(List<MultipartFile> files) throws IOException;
}
