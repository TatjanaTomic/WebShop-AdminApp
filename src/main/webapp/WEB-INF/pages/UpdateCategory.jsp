<%@page import="ip.dto.Attribute"%>
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
	
	<%
	  	Integer id = Integer.parseInt(request.getParameter("id"));
		Category selectedCategory = categoriesBean.getById(id);
		Integer idParentCategory = selectedCategory.getIdParentCategory();
	%>
	
	<h3 class="centeredTitle">Ažuriranje kategorije <b><%=selectedCategory.getName()%></b></h3>

	<p class="resultMessage centeredText"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></p>

	<div class="myContainer">	
		<div class="myContent">
			<h4><%=categoriesBean.getFullName(selectedCategory)%></h4>
			<form action="?action=saveCategory&id=<%=id%>" method="POST" class="categoryForm">
				<div class="mb-3">
	  			  <label for="name" class="form-label fs-5 text-muted">Naziv:</label>
				  <input type="text" class="form-control" name="name" id="name" required
				  value="<%=selectedCategory.getName()%>">
				</div>
				
				<div class="d-grid gap-2">
					<button class="btn btn-warning btn-lg" type="submit" name="submit">Sačuvaj izmjene</button>
		  		</div>
			</form>
			
			
			<h5 style="margin-top:50px; text-align: center;">Dodaj potkategoriju</h5>
			<form action="?action=addSubcategory&id=<%=id%>" method="POST" class="categoryForm">
				<div class="mb-3">
	  			  <label for="nameSubcategory" class="form-label fs-5 text-muted">Naziv:</label>
				  <input type="text" class="form-control" name="nameSubcategory" id="nameSubcategory" required>
				</div>
				
				<div class="d-grid gap-2">
					<button class="btn btn-warning btn-lg" type="submit" name="submit">Dodaj</button>
		  		</div>
			</form>
		</div>
		
		<div class="myContent">
			<h3 class="centeredTitle">Atributi</h3>
			
			<ul class="list-group attributesList">
				<%
	  				for(Attribute a : selectedCategory.getAttributes()) {
	  					if(!a.isDeleted()) {
	  						out.println("<li class=\"list-group-item\">" + "<p width: 250px;>" + a.getName() + "</p>");
	  						out.println("<a class=\"btn btn-outline-danger\" href=\"?action=deleteAttribute&id=" + a.getId() + "\">Obriši</a>");
	  						out.println("</li>");
	  					}
	  					
	  				}
				%>
			</ul>
			
			<form action="?action=saveAttribute&idCategory=<%=id%>&idParentCategory=<%=idParentCategory%>" method="POST" class="attributeForm">
				<div class="mb-3">
	  			  <label for="name" class="form-label fs-5 text-muted">Dodaj novi atribut:</label>
				  <input type="text" class="form-control" name="name" id="name" required placeholder="Naziv">
				</div>
				
				<div class="d-grid gap-2">
					<button class="btn btn-warning btn-lg" type="submit" name="submit">Dodaj atribut</button>
		  		</div>
			</form>
		</div>
		
	</div>
</body>
</html>