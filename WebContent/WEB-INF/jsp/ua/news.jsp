<%@ page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Новини</title>
	<link rel="shortcut icon" href="images/pi.png" type="image/png" />
	<link rel="stylesheet" href="style/menu.css" type="text/css" media="all" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="header">
		<p>Кафедра Вищої та Прикладної Математики</p>
	</div>
	<div class="menu_top">
		<ul id="menu">
			<li>
				<a>Головна</a>
			</li>
			<li>
				<form id="menu_form" action="Controller" method="post">
					<input type="hidden" name="command" value="UA_department" /> 
					<input class="button" type="submit" value="Кафедра" />
				</form>
				<!--  
				<ul>
					<li>
						<form id="menu_form" action="Controller" method="post">
						<input type="hidden" name="command" value="UA_department" /> 
						<input class="button" type="submit" value="Склад кафедри" />
						</form>
					</li>
					<li>
						<form id="menu_form" action="Controller" method="post">
						<input type="hidden" name="command" value="UA_department_histiory" /> 
						<input class="button" type="submit" value="Історія кафедри" />
						</form>
					</li>
				</ul>
				-->	
			</li>
			<li>
				<form id="menu_form" action="Controller" method="post">
					<input type="hidden" name="command" value="UA_science" /> 
					<input class="button" type="submit" value="Наукова діяльність" />
				</form>
				<!--  
				<ul>
					<li>
						<form id="menu_form" action="Controller" method="post">
						<input type="hidden" name="command" value="UA_publishings" /> 
						<input class="button" type="submit" value="Публікації" />
						</form>
					</li>
					<li>
						<form id="menu_form" action="Controller" method="post">
						<input type="hidden" name="command" value="UA_conferences" /> 
						<input class="button" type="submit" value="Конференції" />
						</form>
					</li>
				</ul>
				-->
			</li>
			<li>
				<form id="menu_form" action="Controller" method="post">
					<input type="hidden" name="command" value="UA_students" /> 
					<input class="button" type="submit" value="Студентам" />
				</form>
			</li>
			<li>
				<form id="menu_form" action="Controller" method="post">
					<input type="hidden" name="command" value="UA_entrants" /> 
					<input class="button" type="submit" value="Абітурієнтам" />
				</form>
			</li>
			<li>
				<form id="menu_form" action="Controller" method="post">
					<input type="hidden" name="command" value="UA_contacts" /> 
					<input class="button" type="submit" value="Контакти" />
				</form>
			</li>
		</ul>
	</div>
	<table class="news_list">
		<c:set var="k" value="0"/>
		<c:forEach var="item" items="${news_item}">
			<c:set var="k" value="${k+1}"/>
			<tr class="news_item">
				<td rowspan=4><img class="news_image" src="<%= request.getContextPath() %>${item.image}"/></td>
				<td>
				<tr><td><p class="news_title">${item.title}</p></td></tr>
				<tr><td><p class="news_date">${item.date}</p></td></tr>
				<tr><td><p class="news_text">${item.text}</p></td></tr>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="footer">
		<p>&copy;2016</p>
	</div>
</body>
</html>