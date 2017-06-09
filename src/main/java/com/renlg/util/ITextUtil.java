package com.renlg.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextUtil {
	static Logger logger = Logger.getLogger(ITextUtil.class);

	/**
	 * 文本文件转pdf
	 * 
	 * @param txtPath
	 * @param pdfPath
	 */
	public static void txt2pdf(String txtPath, String pdfPath) {
		InputStreamReader is = null;
		BufferedReader reader = null;
		Document document = null;
		try {
			document = new Document(PageSize.A4);
			is = new InputStreamReader(new FileInputStream(txtPath), getCharset(txtPath));
			reader = new BufferedReader(is);
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// 打开文档，将要写入内容
			document.open();
			String line = reader.readLine();
			while (line != null) {
				Paragraph pg = new Paragraph(line, fontChinese);
				document.add(pg);
				line = reader.readLine();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (document != null) {
					document.close();
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
		}

	}

	/**
	 * 获取文件编码
	 * 
	 * @param fileName
	 * @return
	 */
	private static String getCharset(String fileName) {
		BufferedInputStream bin = null;
		int p = 0;
		try {
			bin = new BufferedInputStream(new FileInputStream(fileName));
			p = (bin.read() << 8) + bin.read();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bin != null) {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String code = null;
		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:
			code = "GBK";
		}
		return code;
	}

	// public void img2pdf(String imgPath, String pdfPath) throws
	// MalformedURLException, IOException, DocumentException {
	// Document document = new Document(PageSize.A4);
	// PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
	// Image img = Image.getInstance(imgPath);
	// // img.scalePercent(35);
	// // img.scalePercent(50,50);
	//
	// // System.out.println("w===="+document.getPageSize().width());
	// // System.out.println("h===="+document.getPageSize().height());
	// // img.scaleAbsolute(595,842);
	// float pdfHeight = document.getPageSize().getHeight();
	// float pdfWidth = document.getPageSize().getWidth() - 70;
	// float imgHeight = img.getPlainHeight();
	// float imgWidth = img.getPlainWidth();
	// // System.out.println("imgwid========"+imgWidth);
	// // System.out.println("imghei========"+imgHeight);
	// if (imgHeight > pdfHeight || imgWidth > pdfWidth) {
	// if (pdfHeight / imgHeight > pdfWidth / imgWidth) {
	// img.scalePercent(pdfWidth / imgWidth * 100);
	// } else {
	// img.scalePercent(pdfHeight / imgHeight * 100);
	// }
	// }
	//
	// document.open();
	// document.add(img);
	// document.close();
	// }

	public static void main(String[] args) throws DocumentException, IOException {
		ITextUtil.txt2pdf("C:\\Users\\renlg\\Desktop\\TestItext.txt", "C:\\Users\\renlg\\Desktop\\TestItext.pdf");
		// test.img2pdf("D:/图片/222.jpg", "F:/test/img2.pdf");
	}
}
