<%@page import="ip.dto.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="categoriesBean" class="ip.bean.CategoriesBean" scope="session"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kategorije</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link href="styles/MainStyle.css" type="text/css" rel="stylesheet">

</head>
<body>
	<%@include file="Header.jsp" %>
	
	<div class="myContainer">
		<div class="myContent">	
			<h3 class="centeredTitle">Pregled svih kategorija proizvoda</h3>
			
			<p class="resultMessage"><%=session.getAttribute("resultNotification")!=null?session.getAttribute("resultNotification").toString():""%></p>
		
			<table class="table table-success table-hover resultTableCategories">
				<thead>
					<tr>
		      			<th scope="col">#</th>
		      			<th scope="col">Naziv</th>
		      			<th scope="col">Ažuriranje</th>
		      			<th scope="col">Brisanje</th>
		    		</tr>
				</thead>
				<tbody>
					<%
						for(Category c : categoriesBean.getCategories()) {
							out.println("<tr><td>" + c.getId() + "</td>");
							out.println("<td>" + c.getName() + "</td>");
							if(!c.isDeleted()) {
								out.println("<td><a class=\"btn btn-outline-warning\" href=\"?action=updateCategory&id=" + c.getId() + "\">Promijeni</a></td>");	
								out.println("<td><a class=\"btn btn-outline-danger\" href=\"?action=deleteCategory&id=" + c.getId() + "\">Obriši</a></td>");	
							}
							else {
								out.println("<td><button type=\"button\" class=\"btn btn-outline-warning\" disabled>Promijeni</button></td>");
								out.println("<td><button type=\"button\" class=\"btn btn-outline-danger\" disabled>Obriši</button></td>");
							}
							out.println("</tr>");
						}
					%>
				</tbody>
			</table>
		</div>
		
		<div class="myContent">
		
		<p class="resultMessage"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></p>
		
			<h3 class="centeredTitle">Nova kategorija</h3>
			
			<form action="?action=addNewCategory" method="POST" class="categoryForm">
				<div class="mb-3">
	  			  <label for="name" class="form-label fs-5 text-muted">Naziv:</label>
				  <input type="text" class="form-control" name="name" id="name" required>
				</div>
				
				<div class="d-grid gap-2">
					<button class="btn btn-warning btn-lg" type="submit" name="submit">Dodaj kategoriju</button>
		  		</div>
			</form>
		</div>
	</div>
</body>
</html>