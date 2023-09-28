<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body class="container-fluid">
	<header>
		<h1>ENI-Enchères</h1>
	</header>
	<main>

		<form action="" method="POST">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Mon Profil</h2>
				</div>

				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="pseudo" class="form-label">Pseudo : </label> <input
						type="text" class="form-control" id="pseudo" name="pseudo"
						required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="nom" class="form-label">Nom : </label> <input
						type="text" class="form-control" id="nom" name="nom"
						required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="prenom" class="form-label">Prénom : </label> <input
						type="text" class="form-control" id="prenom" name="prenom"
						required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="email" class="form-label">Email : </label> <input
						type="email" class="form-control" id="email" name="email"
						required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="telephone" class="form-label">Téléphone : </label> <input
						type="tel" class="form-control" id="telephone" maxlength="12"
						name="telephone" required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="rue" class="form-label">Rue : </label> <input
						type="text" class="form-control" id="rue" name="rue">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="code" class="form-label">Code Postal : </label> <input
						type="text" class="form-control" id="code" maxlength="5"
						name="codepostal" required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="ville" class="form-label">Ville : </label> <input
						type="text" class="form-control" id="ville" name="ville"
						required="required">
				</div>

				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="mdp" class="form-label">Mot de passe : </label> <input
						type="password" class="form-control" id="mdp" required="required">
				</div>
				<div class="col-lg-6 text-center mb-3 mt-3">
					<label for="mdp" class="form-label">Confirmation du mot de
						passe : </label> <input type="password" class="form-control" id="mdp"
						name="password" required="required">
				</div>


				<div class="col-lg-6 text-center">
					<button class="btn btn-success px-5 py-3 " name="save">Créer</button>
				</div>
				<div class="col-lg-6 text-center mt-2">
					<button class="btn btn-warning px-5 py-3">Annuler</button>
				</div>

			</div>
		</form>

	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>