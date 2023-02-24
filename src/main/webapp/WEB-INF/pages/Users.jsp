<%@page import="ip.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="usersBean" class="ip.bean.UsersBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Korisnici</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link href="styles/MainStyle.css" type="text/css" rel="stylesheet">

</head>
<body>
	<%@include file="Header.jsp" %>
	
	<a class="btn btn-outline-primary newUser" href="?action=newUser">Dodaj novog korisnika</a>
	
	<h3 class="centeredTitle">Pregled svih korisničkih naloga</h3>
	
	<p class="resultMessage"><%=session.getAttribute("resultNotification")!=null?session.getAttribute("resultNotification").toString():""%></p>

	<table class="table table-success table-hover resultTable">
		<thead>
			<tr>
      			<th scope="col">#</th>
      			<th scope="col">Ime</th>
      			<th scope="col">Prezime</th>
      			<th scope="col">Korisničko ime</th>
      			<th scope="col">Grad</th>
      			<th scope="col">Mail</th>
      			<th scope="col">Aktiviran nalog</th>
      			<th scope="col">Brisanje naloga</th>
    		</tr>
		</thead>
		<tbody>
			<%
				for(User user : usersBean.getUsers()) {
					out.println("<tr><td>" + user.getId() + "</td>");
					out.println("<td>" + user.getFirstName() + "</td>");
					out.println("<td>" + user.getLastName() + "</td>");
					out.println("<td>" + user.getUsername() + "</td>");
					out.println("<td>" + user.getCity() + "</td>");
					out.println("<td>" + user.getMail() + "</td>");
					
					if(user.isActivated()) {
						out.println("<td>" + "DA" + "</td>");
					}
					else {
						out.println("<td>" + "NE" + "</td>");
					}
					
					if(user.isDeleted()) {
						out.println("<td><button type=\"button\" class=\"btn btn-outline-danger\" disabled>Obriši nalog</button></td>");
					}
					else {
						out.println("<td><a class=\"btn btn-outline-danger\" href=\"?action=delete&id=" + user.getId() + "\">Obriši nalog</a></td>");
					}
					
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>