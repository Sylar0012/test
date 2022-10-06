package site.metacoding.test.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import site.metacoding.test.domain.img.Img;
import site.metacoding.test.domain.img.ImgDao;
import site.metacoding.test.domain.img.ImgDto;

@RequiredArgsConstructor
@Service
public class ImgService {
	
	private final ImgDao imgDao;
	
	public void 사진저장(Img img) {		

		 imgDao.save(img);
	}
	
	public Img 아이디로사진찾기(Integer id) {
		Img img = imgDao.findById(id);
		return img;
	}
}
