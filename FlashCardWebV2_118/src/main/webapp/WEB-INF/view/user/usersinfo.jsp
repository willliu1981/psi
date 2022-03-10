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
	<h1>USERS INFO</h1>
	<hr>
	<table class="evenTable">
		<tr>
			<td colspan="2">USERS INFO</td>
		</tr>
		<tr>
			<td>name</td>
			<td><input type="text" name="display_name"
					value="${user[0].display_name}" /></td>
		</tr>
		<tr>
			<td>username</td>
			<td><input type="text" name="username" value="${user[0].username}" /></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="text" name="password" value="${user[0].password}" /></td>
		</tr>
		<tr>
			<td>age</td>
			<td><input type="text" name="age" value="${user[0].age}" /></td>
		</tr>
		<tr>
			<td>dender</td>
			<td><input type="text" name="gender" value="${user[0].gender}" /></td>
		</tr>
		<tr>
			<td style="text-align: center" colspan="2"><input type="submit"
					value="login" /></td>
		</tr>
	</table>

</body>
</html>