<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.login {
	position: fixed;
	top: 20%;
	left: 30%;
}

.alert {
	padding: 20px;
	background-color: #f44336;
	color: white;
}

.closebtn {
	margin-left: 15px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}
</style>
<title>Connexion</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
</head>
<body class="container-fluid">

	<div class="login">
		<div class="contenu">

			<form action="" method="POST">
				<c:if test="${mustBeLogged}">
					<p class="text-center text-danger">Vous devez être connecté
						pour accéder à cette page.</p>
				</c:if>
				<label class="form-label">Identifiant</label> <input
					class="form-control" type="text" name="identifiant" required /><br />
				<label class="form-label">Mot de passe</label> <input
					class="form-control" type="password" name="psw" required /><br />
				<c:if test="${login_error}">
					<p class="text-danger text-center">Identifiant ou mot de passe
						incorrect</p>
				</c:if>
				<button name="login" class="btn btn-primary">LogIn</button>
				<input type="checkbox">Se souvenir de moi<br /> <a href="#"
					class="forgot">Forgot password?</a>
			</form>
			<form action="">
				<button class="btn btn-secondary" name="newU">Créer un
					compte</button>
			</form>

			<c:if test="${invalide!=null}">
				<div class="alert">
					<span class="closebtn"
						onclick="this.parentElement.style.display='none';">&times;</span>
					<strong>Attention!</strong> L'identifiant et/ou le mot de passe
					saisie est incorrect !
				</div>
			</c:if>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</body>
</html>