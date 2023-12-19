package com.ssafy.file.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.model.FileDto;

import java.util.List;

@Mapper
public interface FileDao {
	public int insertFile(FileDto file);
	public int updateFile(MultipartFile file);
	public int deleteFile(FileDto fileDto);
	public List<FileDto> selectFileAll(int contentId);
	public List<FileDto> selectUserImages(String userId);
}
