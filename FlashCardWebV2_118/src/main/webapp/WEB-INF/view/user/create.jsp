<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FLASH CARD</title>
<link rel="styleSheet" type="text/css"
	href="${pageContext.request.contextPath}/css/EvenTable.css">
</head>
<body>
	<h1>CREATE USER</h1>
	<hr>
	<form action="${pageContext.request.contextPath}/user" method="POST">
		<table class="evenTable">
			<tr>
				<td colspan="2">CREATE USER</td>
			</tr>
			<tr>
				<td>username</td>
				<td><input type="text" name="username" value="${user.username}" /></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="text" name="password" value="${user.password}" /></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" name="display_name"
						value="${user.display_name}" /></td>
			</tr>
			<tr>
				<td>age</td>
				<td><input type="text" name="age" value="${user.age}" /></td>
			</tr>
			<tr>
				<td>dender</td>
				<td><input type="text" name="gender" value="${user.gender}" /></td>
			</tr>
			<tr>
				<td style="text-align: center" colspan="2"><input type="submit"
						value="login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>