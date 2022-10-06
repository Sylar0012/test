package site.metacoding.test.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.RequiredArgsConstructor;
import site.metacoding.test.domain.img.Img;
import site.metacoding.test.domain.img.ImgDto;
import site.metacoding.test.service.ImgService;

@RequiredArgsConstructor
@Controller
public class TestController {

	private final ImgService imgService;

	@GetMapping("/")
	public String main() {
		return "index";
	}

	@GetMapping("/imgtest/imgView/{id}")
	public String imgView(@PathVariable Integer id, Model model) {
		Img img = imgService.아이디로사진찾기(id);
		System.out.println("=====================");
		System.out.println(img.getImgName());
		System.out.println(img.getId());
		System.out.println("=====================");
	
		model.addAttribute("img", img);
		return "imgtest/imgView";
	}

	@GetMapping("/imgtest/imgSaveForm")
	public String imgSaveForm() {
		return "imgtest/imgSaveForm";
	}

	@PostMapping("/imgtest/img")
	public @ResponseBody CMRespDto<?> create(MultipartHttpServletRequest request, ImgDto imgDto) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MultipartFile file = request.getFile("file");
		
		// 파일이름에서 " . " 이후의 문자열이 확장자가 됨.
		int pos = file.getOriginalFilename().lastIndexOf(".");

		// 확장자명을 나중에 합치기 위한 작업.
		String extension = file.getOriginalFilename().substring(pos + 1);

		// 경로지정하는 구간.
		String filePath = "E:\\workspace\\spring_blog_lib_copy\\test\\src\\main\\resources\\static\\img\\";

		// 파일명을 UUID화 하여 중복을 방지하고
		String imgSaveName = UUID.randomUUID().toString();

		// UUID화 한 파일명 + 확장자
		String imgName = imgSaveName + "." + extension;

		File dest = new File(filePath, imgName);
		try {
			Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("=====================");
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		System.out.println("imgName : " +  imgName);
		System.out.println("=====================");

		Img img = imgDto.toEntity(imgName);
		
		imgService.사진저장(img);
		return new CMRespDto<>(1, "파일저장성공", imgName);
	}
	
}
