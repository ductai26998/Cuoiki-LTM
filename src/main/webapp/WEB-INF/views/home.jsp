<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Collection</title>
<style>
	html, body {
		margin: 0px;
		width: 100%;
		height: 100%;
	}
	html, #flex-container-inner {
		height: 100%;
		display: flex;
		flex-wrap: nowrap;
	}
	#header {
	  background-color: DodgerBlue;
	  color: rgb(247, 247, 247);
	  text-align: left;
	  padding: 1px;
	  margin-top: 0;
	}
	#left-div {
		background-color: rgba(236, 239, 240, 0.918);
		flex: 1;
	}
	#content-view {
		background-color: rgba(253, 253, 253, 0.918);
		flex: 6;
	}
	hr.solid {
		margin-top: 60px;
  		border-top: 1px solid rgb(236, 236, 236);
	}
	#file {
		font-family: Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		width: 96%;
		margin: auto;
	}

	#file td, #file th {
		border: 1px solid #ddd;
		padding: 8px;
	}

	/* #file tr:nth-child(even){background-color: #f2f2f2;} */

	#file tr:hover {background-color: #ddd;}

	#file th {
		padding-top: 12px;
		padding-bottom: 12px;
		text-align: left;
		background-color: DodgerBlue;
		color: white;
	}
	
	.file-thumbnail {
		width: 200px;
		height: 200px;
	}
	</style>
</head>
<body>
	<div id="header">
		<p style="font-size:16px;margin-left: 18px;font-weight: 400;">Office 366</p>
	</div>
	<div id="flex-container-inner">
		<div id="left-div">
			<div style="margin-bottom: 12px; margin-top: 20px;">
				<a style="text-decoration: none;margin: 12px;color: rgb(26, 34, 151);" href="url">Tải về ứng dụng TwoDrive</a>
			</div>
			<div>
				<a style="text-decoration: none;margin: 12px;color: rgb(26, 34, 151);" href="url">Thông Tin Ứng Dụng</a>
			</div>
		</div>
		<div id="content-view">
			<p style="font-size: 20px; margin-left: 30px;font-weight: bold;">Tệp của tôi</p>
			<hr class="solid" style="margin-left: 30px; margin-top: 10px; margin-right: 30px;">
			<form style="margin-bottom: 30px; margin-top: 20px" method="post" action="home" enctype="multipart/form-data">
				<span style="font-size:18px;margin-left: 30px;font-weight: 500;">Select file to upload: </span><input type="file" name="file" size="60" />
				<input style="padding: 4px 16px;" type="submit" value="Upload" />
			</form>
			<table id="file">
				<tr>
					<th>Name</th>
					<th>Type</th>
					<th>Size</th>
					<th></th>
				</tr>
				<c:forEach items="${files}" var="file">
					<tr>
						<td>${ file.name }</td>
						<td>${ file.type }</td>
						<td>${ file.size }</td>
						<td>
							<img class="file-thumbnail" src="${ file.url }" />
						</td>
					</tr>
				</c:forEach>
				</table>
		</div>
	</div>
</body>
</html>