package com.ssafy.file.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.model.FileDto;

import java.util.List;

public interface FileService {
	public int insertFile(FileDto file);
	public int updateFile(MultipartFile file);
	public int deleteFile(FileDto fileDto) throws Exception;
	public List<FileDto> selectFileAll(int contentId);
	public List<FileDto> selectUserImages(String userId) throws Exception;
}

