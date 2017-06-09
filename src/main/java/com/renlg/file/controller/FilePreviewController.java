package com.renlg.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.renlg.base.BaseController;
import com.renlg.base.JsonResult;
import com.renlg.file.service.FilePreviewService;

@RequestMapping("filePreview")
@Controller
public class FilePreviewController extends BaseController {

	@Autowired
	private FilePreviewService previewService;

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	@RequestMapping("transformWithOpenOffice")
	@ResponseBody
	public JsonResult transformWithOpenOffice(MultipartFile file, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		String contextPath = request.getContextPath();
		String resultUrl = previewService.transform(file, contextPath);
		result.setModel(resultUrl);
		return result;
	}
	
	/**
	 * 上传文件
	 * 
	 * @return
	 */
	@RequestMapping("transformWithJacob")
	@ResponseBody
	public JsonResult transformWithJacob(MultipartFile file, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		String contextPath = request.getContextPath();
		String resultUrl = previewService.tansformWithJacob(file, contextPath);
		result.setModel(resultUrl);
		return result;
	}

	/**
	 * 下载
	 * 
	 * @param path
	 */
	@RequestMapping("download/**")
	@ResponseBody
	public void download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "isInline", defaultValue = "true") boolean isInline) {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.indexOf("download") + 9);
		File resultFile = previewService.getFile(path);
		String userAgent = request.getHeader("User-Agent");
		String fileName = resultFile.getName();
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} else {
				// 非IE浏览器的处理：
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			response.setCharacterEncoding("utf-8");
//			response.setContentType("multipart/form-data");
			// response.setHeader("Content-Encoding", "gzip");
			// response.setHeader("Accept-Ranges", "bytes");
			// response.setHeader("Content-Length", resultFile.length() + "");
			// response.setContentType("application/x-shockwave-flash;charset=UTF-8");
			if (isInline) {
				response.setHeader("Content-Disposition", "inline; filename=" + fileName);
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			}
			// response.setHeader("Content-Disposition", "attachment; filename="
			// + fileName);

			inputStream = new FileInputStream(resultFile);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
	}

	/**
	 * 文档预览
	 * 
	 * @param request
	 * @param fileId
	 * @return
	 */
	@RequestMapping("doc-preview")
	public String docPreview(HttpServletRequest request, String fileId) {
		request.setAttribute("fileId", fileId);
		return "/preview/doc-preview";
	}

	/**
	 * 文档预览
	 * 
	 * @param request
	 * @param fileId
	 * @return
	 */
	@RequestMapping("pdf-preview")
	public String pdfPreview(HttpServletRequest request, String fileId) {
		request.setAttribute("fileId", fileId);
		return "/preview/pdf-preview";
	}
}
