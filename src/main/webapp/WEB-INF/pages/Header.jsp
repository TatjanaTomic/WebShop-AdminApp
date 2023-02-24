<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminBean" class="ip.bean.AdminBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>

<style>
	h3 {
		margin-right: 50px;
	}
</style>

</head>
<body>
	<nav class="navbar navbar-succes bg-success">
	  <div class="container-fluid">
		<h3>AdminAppIP</h3>
	    <a class="navbar-brand btn btn-success" href="?action=categories">Kategorije</a>
	    <a class="navbar-brand btn btn-success" href="?action=users">Korisnici</a>
	    <a class="navbar-brand btn btn-success" href="?action=logs">Statistika</a>
	    
	    <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
	        <span class="me-3 py-2 text-dark text-decoration-none">Administrator: <%=adminBean.getAdmin().getFirstName()%> <%=adminBean.getAdmin().getLastName()%></span>
	        <a class="btn btn-secondary" href="?action=logout">Odjavi se</a>
	    </nav>
	  </div>
	</nav>
</body>
</html>