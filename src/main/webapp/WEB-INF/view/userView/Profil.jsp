<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
	li{
	  list-style-type:none;
	}
	</style>
</head>
<body>

	<header>
		<%@ include file="//Header.jsp"%>

		<h1 class="text-center">Profil</h1>
	</header>
	<main>

		<form action="" method="POST">
			
				<div class="text-center">
					<span class="text-center mb-3 mt-3">Pseudo :
						${nomUser.getPseudo()} </span>

				</div>
				<div class="text-center">
					<span class="text-center mb-3 mt-3">Nom : ${nomUser.getNom()} </span>

				</div>
				<div class="text-center">
					<span class="text-center mb-3 mt-3">Prénom :
						${nomUser.getPrenom()} </span>

				</div>
				<div class="text-center">
					<span class=" mb-3 mt-3">Email :
						${nomUser.getEmail()} </span>

				</div>
				<div class="text-center">
					<span class="text-center mb-3 mt-3">Téléphone :
						${nomUser.getTelephone()} </span>

				</div>
				<div class="text-center">
					<span class="mb-3 mt-3">Rue : ${nomUser.getRue()} </span>

				</div>
				<div class="text-center">
					<span class=" mb-3 mt-3">Code Postal :
						${nomUser.getCodePostal()} </span>

				</div>
				<div class="text-center">
					<span class="mb-3 mt-3">Ville :
						${nomUser.getVille()} </span>

				</div>
				<div class="text-center">
					<span class=" mb-3 mt-3">Crédit :
						${nomUser.getCredit()}</span>
				</div>
				<div class="text-center">
					<button name="modif">Modifier Profil</button>
				</div>
				<div class="text-center">
					<button name="Index">Retour</button>
				</div>

				</form>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>