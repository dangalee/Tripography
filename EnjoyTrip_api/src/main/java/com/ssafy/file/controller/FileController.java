package com.ssafy.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.file.model.FileDto;
import com.ssafy.file.model.service.FileService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

	FileService fileService;


	@Value("${file.path.upload-images}")
	private String uploadImagePath;


	@Autowired
	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@PostMapping("/uploadPhoto")
	public ResponseEntity<FileDto> uploadImage(@RequestParam("userId") String userId,
										 @RequestParam("contentId") String contentId,
										 @RequestPart("file") MultipartFile file) throws IOException {
		

		FileDto fileDto = null;
		if (!file.isEmpty()){

			//contentId로 폴더 만들기
			String saveFolder = uploadImagePath + File.separator + contentId;

			File folder = new File(saveFolder);
			if (!folder.exists()) folder.mkdirs();

			String originalFileName = file.getOriginalFilename();
			String saveFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf('.'));
			fileDto = new FileDto(0, userId, Integer.parseInt(contentId),saveFileName, contentId, originalFileName,"");
			file.transferTo(new File(folder, saveFileName));
			fileService.insertFile(fileDto);

		}


		return new ResponseEntity<>(fileDto, HttpStatus.OK);
	}

	@PostMapping("/{contentId}")
	public ResponseEntity<List<FileDto>> showImages(@PathVariable("contentId") int contentId) {
		List<FileDto> files = fileService.selectFileAll(contentId);
		return new ResponseEntity<>(files, HttpStatus.OK);
	}

	@PutMapping("/updatePhoto")
	public ResponseEntity<?> updateImage(MultipartFile file) {
		return null;
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> removeImage(@RequestBody FileDto fileDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.error(fileDto.toString());
			fileService.deleteFile(fileDto);
			resultMap.put("message", "사진을 삭제하였습니다!");
			status = HttpStatus.OK;
		}catch(Exception e) {
			log.error("사진 삭제 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getPhotoList(@PathVariable String userId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.error("userID : {}",userId);
			List<FileDto> list = fileService.selectUserImages(userId);
			resultMap.put("photolist", list);
			status = HttpStatus.OK;
		}catch(Exception e) {
			log.error("좋아요 조회 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
	}
}
