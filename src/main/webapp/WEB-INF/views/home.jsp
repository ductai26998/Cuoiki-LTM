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
	  padding-right: 20px;
	  margin-top: 0;
	  display: flex;
	  flex-direction: row;
	  justify-content: space-between
	}
	.user-info{
		display: flex;
		flex-direction: row;
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
		width: 320px;
		height: 240px;
	}
	</style>
</head>
<body>
	<div id="header">
		<p style="font-size:16px;margin-left: 18px;font-weight: 600;">My Collections</p>
		<c:if test="${ username != null }">
		<div class="user-info">
			<p style="margin-right: 10px; font-weight: 600;">${ username }</p>
			<p>
				<a href="logout" style="color: white;">Logout</a>
			</p>
		</div>
		</c:if>
		<c:if test="${ username == null }">
		<div class="user-info">
			<p style="margin-right: 10px;">
				<a href="login" style="color: white;">Login</a>
			</p>
			<p>
				<a href="register" style="color: white;">Register</a>
			</p>
		</div>
		</c:if>
	</div>
	<div id="flex-container-inner">
		<c:if test="${ username == null }">
			<h3 style="padding-left: 20px;">
				Please login
				<a href="login" >here</a>
				to use application !!!
			</h3>
		</c:if>
		<c:if test="${ username != null }">
		<div id="content-view">
			<p style="font-size: 20px; margin-left: 30px;font-weight: bold;">All files</p>
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
					<th></th>
				</tr>
				<c:forEach items="${files}" var="file">
					<tr>
						<td>${ file.name }</td>
						<td>${ file.type }</td>
						<td>${ file.size }KB</td>
						<td>
							<c:if test="${ file.type.contains('image') }">
								<img class="file-thumbnail" src="${ file.url }" />
							</c:if>
							<c:if test="${ file.type.contains('video') }">
								<video width="320" height="240" controls>
  								<source src="${ file.url }" type="${ file.type }">
							</video>
							</c:if>
						</td>
						<td>
							<a href="deleteFile?id=${file.id}" role="button">Delete</a>
						</td>
					</tr>
				</c:forEach>
				</table>
		</div>
		</c:if>
	</div>
</body>
</html>