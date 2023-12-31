package com.ssafy.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
	private int fileId;
	private String userId;
	private int contentId;
	private String saveFile;
	private String saveFolder;
	private String originalFile;
	private String title;
}

