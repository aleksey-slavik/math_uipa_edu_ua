<%@ page language="java" contentType="text/html; windows-1251"
	pageEncoding="windows-1251"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; windows-1251">
<title>���������</title>
</head>
<body>
	<p align="center">������ ��� ���</p>
	<form name="send" method="get" action="/SendServlet"></form>
	<table align="center">
		<tr>
			<td align="right">
				<p>ͳ�:</p>
			</td>
			<td>
				<input type="text" name="nickname" />
			</td>
		</tr>
		<tr>
			<td align="right">
				<p>����� ��������:</p>
			</td>
			<td>
				<input type="text" name="phone" />
			</td>
		</tr>
	</table>
	<p align="center">
		<input type="submit" name="submit" value="³��������"/>
	</p>
</body>
</html>