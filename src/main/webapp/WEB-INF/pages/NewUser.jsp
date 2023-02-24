<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novi korisnik</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link href="styles/MainStyle.css" type="text/css" rel="stylesheet">

</head>
<body>
	<h2 class="centeredTitle">Dodavanje novog korisnika</h2>

	<form method="POST" action="?action=addNewUser" class="addUserForm">
		<div class="mb-3">
		  <input type="text" class="form-control" name="firstName" id="firstName" required placeholder="Ime">
		</div>
		<div class="mb-3">
		  <input type="text" class="form-control" name="lastName" id="lastName" required placeholder="Prezime">
		</div>
		<div class="mb-3">
		  <input type="text" class="form-control" name="username" id="username" required placeholder="Korisničko ime">
		</div>
		<div class="mb-3">
		  <input type="text" class="form-control" name="mail" id="mail" required placeholder="Email">
		</div>
		<div class="mb-3">
		  <input type="text" class="form-control" name="city" id="city" required placeholder="Grad">
		</div>
		<div class="mb-3">
		  <input type="text" class="form-control" name="avatar" id="avatar" placeholder="Avatar">
		</div>
		
		<p class="resultMessage"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></p>

		<div class="d-grid gap-2 col-6 mx-auto">
  			<button class="btn btn-warning btn-lg" type="submit" name="submit">Dodaj korisnika</button>
  			<a class="text-dark btn btn-link" href="?action=users">Vrati se na prethodnu stranu...</a>
		</div>
	</form>
	
</body>
</html>