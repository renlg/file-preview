<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文档预览演示</title>
<script
	src="${contextPath}/assets/dep/plupload/2.2.0/plupload.full.min.js"></script>
<script src="${contextPath}/assets/dep/jquery/2.1.3/jquery.js"></script>
</head>
<body>
	<!-- 这里我们只使用最基本的html结构：一个选择文件的按钮，一个开始上传文件的按钮(甚至该按钮也可以不要) -->
	<p>
		<button id="browse">选择文件</button>
		<button id="start_upload">开始上传</button>
	</p>
	<script>
		//实例化一个plupload上传对象
		var uploader = new plupload.Uploader({
			browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
			url : '${contextPath}/filePreview/transformWithJacob', //服务器端的上传页面地址
		});

		//在实例对象上调用init()方法进行初始化
		uploader.init();

		//绑定各种事件，并在事件监听函数中做你想做的事
		uploader.bind('FilesAdded', function(uploader, files) {
			//每个事件监听函数都会传入一些很有用的参数，
			//我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
		});
		uploader.bind('UploadProgress', function(uploader, file) {
			//每个事件监听函数都会传入一些很有用的参数，
			//我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
		});
		//......
		//......

		//最后给"开始上传"按钮注册事件
		document.getElementById('start_upload').onclick = function() {
			uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
		}

		uploader.bind('FileUploaded', function(_uploader, files, response) {
			var result = $.parseJSON(response.response);
			if(result.success){
				window.open(result.model)
			}else{
				alert(result.message)
			}
			console.info(response)
		});
	</script>
</body>
</html>