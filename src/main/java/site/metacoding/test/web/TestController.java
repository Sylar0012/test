package site.metacoding.test.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import site.metacoding.test.domain.img.Img;
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
	public @ResponseBody String create(@RequestPart MultipartFile file) {

		// 파일이름에서 " . " 이후의 문자열이 확장자가 됨.
		int pos = file.getOriginalFilename().lastIndexOf(".");

		// 확장자명을 나중에 합치기 위한 작업.
		String extension = file.getOriginalFilename().substring(pos + 1);

		// 경로지정하는 구간.
		String filePath = "D:\\workspace\\spring_project_lab\\test\\src\\main\\resources\\static\\assets\\";
		
		// 파일명을 UUID화 하여 중복을 방지하고
		String imgName = UUID.randomUUID().toString();

		// 경로 + UUID화 한 파일명 + 확장자
		String imgLocation = filePath + imgName + "." + extension;

		System.out.println("imgLocation : " + imgLocation);

		File dest = new File(imgLocation);
		try {
			Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imgService.사진저장(imgLocation);
		return "ok";
	}

}
