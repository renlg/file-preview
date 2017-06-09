package com.renlg.util;

import java.util.HashSet;
import java.util.Set;

public class FileTypeUtil {
	public static final Set<String> IMAGE_TYPE_SET = new HashSet<>();
	
	public static final Set<String> DOC_TYPE_SET = new HashSet<>();
	
	public static final Set<String> ZIP_TYPE_SET = new HashSet<>();
	
	public static final Set<String> TXT_TYPE_SET = new HashSet<>();
	
	public static final Set<String> VIDEO_TYPE_SET = new HashSet<>();
	
	static{
		initImageSet();
		initDocSet();
		initZipSet();
		initTxtSet();
		initVideoSet();
	}
	
	private static void initImageSet(){
		IMAGE_TYPE_SET.add("BPMN");
		IMAGE_TYPE_SET.add("JPG");
		IMAGE_TYPE_SET.add("JPEG");
		IMAGE_TYPE_SET.add("PNG");
		IMAGE_TYPE_SET.add("GIF");
	}
	
	private static void initDocSet(){
		DOC_TYPE_SET.add("DOC");
		DOC_TYPE_SET.add("DOCX");
		DOC_TYPE_SET.add("PPT");
		DOC_TYPE_SET.add("PPTX");
		DOC_TYPE_SET.add("XLS");
		DOC_TYPE_SET.add("XLSX");
	}
	private static void initZipSet(){
		ZIP_TYPE_SET.add("ZIP");
		ZIP_TYPE_SET.add("RAR");
		ZIP_TYPE_SET.add("7Z");
	}
	private static void initTxtSet(){
		TXT_TYPE_SET.add("TXT");
		TXT_TYPE_SET.add("JS");
		TXT_TYPE_SET.add("CSS");
		TXT_TYPE_SET.add("LOG");
		TXT_TYPE_SET.add("INI");
		TXT_TYPE_SET.add("REG");
		TXT_TYPE_SET.add("INF");
		TXT_TYPE_SET.add("JAVA");
		TXT_TYPE_SET.add("HTML");
	}
	
	private static void initVideoSet(){
		
	}
	
	private FileTypeUtil(){
		
	}
	
	private static String getExtensionUp(String extension){
		return extension.replace(".", "").toUpperCase();
	}
	
	public static boolean isImage(String extension){
		extension = getExtensionUp(extension);
		if(IMAGE_TYPE_SET.contains(extension)){
			return true;
		}
		return false;
	}
	
	public static boolean isDoc(String extension){
		extension = getExtensionUp(extension);
		if(DOC_TYPE_SET.contains(extension)){
			return true;
		}
		return false;
	}
	
	public static boolean isZip(String extension){
		extension = getExtensionUp(extension);
		if(ZIP_TYPE_SET.contains(extension)){
			return true;
		}
		return false;
	}
	
	public static boolean isTxt(String extension){
		extension = getExtensionUp(extension);
		if(TXT_TYPE_SET.contains(extension)){
			return true;
		}
		return false;
	}
	
	public static boolean isVideo(String extension){
		extension = getExtensionUp(extension);
		if(VIDEO_TYPE_SET.contains(extension)){
			return true;
		}
		return false;
	}
}
