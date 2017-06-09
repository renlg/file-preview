<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%x>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>文档预览</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${contextPath}/assets/js/swfobject.js"></script>
<script type="text/javascript">
	function createRecorder() {
		var flashvars = {};
		var params = {};
		var attributes = {}
		swfobject.embedSWF("${contextPath}/filePreview/download/swf/${fileId}",
				"myContent", "1240", "1754", "9.0.0",
				"${contextPath}/filePreview/download/swf/${fileId}", flashvars,
				params, attributes);
	}
	createRecorder();
	/*${contextPath}/assets/static/test.swf 	assetsswfobject.embedSWF("${contextPath}/filePreview/download/swf/${fileId}", "myContent",
	 "1240", "1754", "9.0.0", "${contextPath}/filePreview/download/swf/${fileId}"); */
</script>
</head>
<body>
	<div id="myContent">
		<h1>Alternative content</h1>
		<p>
			<a href="http://www.adobe.com/go/getflashplayer"><img
				src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
				　　　　　　　　　　　　alt="Get Adobe Flash player" /></a>
		</p>
	</div>
</body>
</html>