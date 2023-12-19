package com.ssafy.file.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.model.FileDto;
import com.ssafy.file.model.dao.FileDao;

import java.util.List;


@Service
public class FileServiceImpl implements FileService{

	FileDao fileDao;

	@Autowired
	public FileServiceImpl(FileDao fileDao) {
		super();
		this.fileDao = fileDao;
	}

	@Override
	public int insertFile(FileDto file) {
		// TODO Auto-generated method stub
		fileDao.insertFile(file);
		return 0;
	}

	@Override
	public int updateFile(MultipartFile file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFile(FileDto fileDto) {
		return fileDao.deleteFile(fileDto);
	}

	@Override
	public List<FileDto> selectFileAll(int contentId) {
		// TODO Auto-generated method stub
		return fileDao.selectFileAll(contentId);
	}

	@Override
	public List<FileDto> selectUserImages(String userId) throws Exception {
		return fileDao.selectUserImages(userId);
	}

}

