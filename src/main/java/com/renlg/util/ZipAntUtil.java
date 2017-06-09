package com.renlg.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipAntUtil {

	public static void compress(String srcPath, String outFilePath) {
		File zipFile = new File(outFilePath);
		File srcFile = new File(srcPath);
		if (!srcFile.exists()) {
			throw new RuntimeException("目标文件不存在");
		}
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcFile);
		// fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
		// eg:zip.setIncludes("*.java");
		// fileSet.setExcludes(...); 排除哪些文件或文件夹
		zip.addFileset(fileSet);
		zip.execute();
	}

	public static void main(String[] args) {
		ZipAntUtil.compress("D:\\test\\pdf", "D:\\test\\pdf.zip");
	}
}
