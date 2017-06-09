package com.renlg.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.renlg.base.BaseService;
import com.renlg.file.service.FilePreviewService;
import com.renlg.util.FileTypeUtil;
import com.renlg.util.FileUtil;
import com.renlg.util.ITextUtil;
import com.renlg.util.JacobUtil;
import com.renlg.util.OpenOfficeUtil;
import com.renlg.util.PropertiesUtil;

@Service
public class FilePreviewServiceImpl extends BaseService implements FilePreviewService {

	@Override
	public String transform(MultipartFile file, String contextPath) {
		String result = "";
		String fileRootPath = FileUtil.getRootPath();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString();
		if (StringUtils.isEmpty(extension)) {
			try {
				extension = FileUtil.getFileTypeByInputStream(file.getInputStream());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		if (StringUtils.isEmpty(extension)) {
			throw new RuntimeException("未知格式");
		} else if (FileTypeUtil.isTxt(extension)) {
			File uploadFile = new File(fileRootPath + fileName + ".odt");
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				logger.error(e.getMessage(), e);
			}
			OpenOfficeUtil util = new OpenOfficeUtil(uploadFile.getAbsolutePath(),
					PropertiesUtil.getAppByKey("SWFTOOLS_PATH"));
			boolean conver = util.conver();
			if (conver) {
				result = contextPath + "filePreview/doc-preview?fileId=" + fileName + ".swf";
			} else {
				throw new RuntimeException("打开文件失败");
			}
		} else if (FileTypeUtil.isImage(extension)) {

		} else if (FileTypeUtil.isDoc(extension)) {
			File uploadFile = new File(fileRootPath + fileName + "." + extension);
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				logger.error(e.getMessage(), e);
			}
			OpenOfficeUtil util = new OpenOfficeUtil(uploadFile.getAbsolutePath(),
					PropertiesUtil.getAppByKey("SWFTOOLS_PATH"));
			boolean conver = util.conver();
			if (conver) {
				result = contextPath + "/filePreview/doc-preview?fileId=" + fileName + ".swf";
			} else {
				throw new RuntimeException("打开文件失败");
			}
		} else if (FileTypeUtil.isZip(extension)) {

		} else if (FileTypeUtil.isVideo(extension)) {

		} else {
			throw new RuntimeException("此格式" + extension + "不支持在线预览");
		}
		return result;
	}

	@Override
	public File getFile(String path) {
		String fileRootPath = FileUtil.getRootPath();
		File file = new File(fileRootPath + path);
		if (!file.exists()) {
			throw new RuntimeException("文件不存在");
		}
		return file;
	}

	@Override
	public String tansformWithJacob(MultipartFile file, String contextPath) {
		String result = "";
		String fileRootPath = FileUtil.getRootPath();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString();
		if (StringUtils.isEmpty(extension)) {
			try {
				extension = FileUtil.getFileTypeByInputStream(file.getInputStream());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		File uploadFile = new File(fileRootPath + fileName + "." + extension);
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e);
		}

		if (StringUtils.isEmpty(extension)) {
			throw new RuntimeException("未知格式");
		} else if (FileTypeUtil.isTxt(extension)) {
			ITextUtil.txt2pdf(uploadFile.getAbsolutePath(), fileRootPath + "pdf" + File.separator + fileName + ".pdf");
			result = contextPath + "/filePreview/pdf-preview?fileId=" + fileName + ".pdf";
		} else if (FileTypeUtil.isImage(extension)) {

		} else if (FileTypeUtil.isDoc(extension)) {
			JacobUtil.conver2Pdf(uploadFile.getAbsolutePath(),
					fileRootPath + "pdf" + File.separator + fileName + ".pdf");
			result = contextPath + "/filePreview/pdf-preview?fileId=" + fileName + ".pdf";
		} else if (FileTypeUtil.isZip(extension)) {

		} else if (FileTypeUtil.isVideo(extension)) {

		} else {
			throw new RuntimeException("此格式" + extension + "不支持在线预览");
		}
		return result;
	}

}
