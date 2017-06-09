<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>文档预览</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${contextPath}/assets/js/pdfobject.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var success = new PDFObject({
			//url : "${contextPath}/assets/static/pdf/test.pdf",
			url : "${contextPath}/filePreview/download/pdf/${fileId}",
			pdfOpenParams : {
				scrollbars : '0',
				toolbar : '0',
				statusbar : '0'
			}
		}).embed("pdf1");
	};
	/*${contextPath}/assets/static/test.swf 	assetsswfobject.embedSWF("${contextPath}/filePreview/download/swf/${fileId}", "myContent",
	 "1240", "1754", "9.0.0", "${contextPath}/filePreview/download/swf/${fileId}"); */
</script>
</head>
<body>
	<div id="pdf1" style="width:700px; height:600px;">It appears you don't have Adobe Reader or PDF support in this web browser. <a href="~/pdf/CGVET22-08-2011V2P.pdf">Click here to download the PDF</a></div>
</body>
</html>