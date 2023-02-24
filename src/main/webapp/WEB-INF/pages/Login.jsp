<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminApp - Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link href="styles/Style.css" type="text/css" rel="stylesheet">
</head>
<body class="loginBackground">
	<div class="loginContent">
		<h1>AdminAppIp</h1>
	
		<form method="POST" action="?action=login">
			<div class="mb-3">
	  			<label for="username" class="form-label fs-5 text-muted">Korisničko ime:</label>
	  			<input type="text" name="username" class="form-control" id="username" required>
			</div>
			<div class="mb-3">
	  			<label for="password" class="form-label fs-5 text-muted">Lozinka:</label>
	  			<input type="password" name="password" class="form-control" id="password" required>
			</div>
			<p class="errorMessage"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></p>
			<div class="d-grid gap-2 col-6 mx-auto">
	  			<button class="btn btn-success btn-lg" type="submit" name="submit">Prijavi se</button>
			</div>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>