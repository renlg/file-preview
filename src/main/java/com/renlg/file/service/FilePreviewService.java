package com.renlg.file.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FilePreviewService {
	public String transform(MultipartFile file,String contextPath);
	
	public String tansformWithJacob(MultipartFile file,String contextPath);
	
	public File getFile(String path);
}
