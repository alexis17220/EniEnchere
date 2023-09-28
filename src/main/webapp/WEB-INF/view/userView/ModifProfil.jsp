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

</head>
<body>

	<h2>Mon Profil</h2>
	<main>

		<form action="" method="POST">
			<div class="row">
				<input type="hidden" name="id" value="${nomUser.getNoUtilisateur()}" />
				<input type="hidden" name="credit" value="${nomUser.getCredit()}" /> <input
					type="hidden" name="admin" value="${nomUser.isAdministrateur()}" />
				<div class="col-lg-4">
					<label for="pseudo" class="form-label">Pseudo : </label> <input
						type="text" class="form-control" id="pseudo" name="pseudo"
						value="${nomUser.getPseudo()}">
				</div>
				<div class="col-lg-4">
					<label for="nom" class="form-label">Nom : </label> <input
						type="text" class="form-control" id="nom" name="nom"
						value="${nomUser.getNom()}">
				</div>
				<div class="col-lg-4">
					<label for="prenom" class="form-label">Prénom : </label> <input
						type="text" class="form-control" id="prenom" name="prenom"
						value="${nomUser.getPrenom()}">
				</div>
				<div class="col-lg-4">
					<label for="email" class="form-label">Email : </label> <input
						type="email" class="form-control" id="email" name="mail"
						value="${nomUser.getEmail()}">
				</div>
				<div class="col-lg-4">
					<label for="telephone" class="form-label">Téléphone : </label> <input
						type="tel" class="form-control" id="telephone" name="tel"
						value="${nomUser.getTelephone()}">
				</div>
				<div class="col-lg-4">
					<label for="rue" class="form-label">Rue : </label> <input
						type="text" class="form-control" id="rue" name="rue"
						value="${nomUser.getRue()}">
				</div>
				<div class="col-lg-4">
					<label for="code" class="form-label">Code Postal : </label> <input
						type="text" class="form-control" id="code" name="code"
						maxlength="5" value="${nomUser.getCodePostal()}">
				</div>
				<div class="col-lg-4">
					<label for="ville" class="form-label">Ville : </label> <input
						type="text" class="form-control" id="ville" name="ville"
						value="${nomUser.getVille()}">
				</div>
				<div class="col-lg-4">
					<label for="mdp" class="form-label">Mot de passe actuel : </label>
					<input disabled type="password" class="form-control" id="mdp"
						value="${nomUser.getMotDePasse()}">
				</div>
				<div class="col-lg-4">
					<label for="mdp" class="form-label">Nouveau Mot de passe :
					</label> <input type="password" class="form-control" id="mdp�">
				</div>
				<div class="col-lg-4">
					<label for="mdp" class="form-label">Confirmation de mot de
						passe : </label> <input type="password" class="form-control" id="mdp"
						name="mdp">
				</div>

				<div class="col-lg-4">
					<span class="text-center mb-3 mt-3">Crédit :
						${nomUser.getCredit()} </span>

				</div>

				<div>
					<button class="btn btn-warning" name="save">Enregistrer</button>
				</div>
				<div>
					<button class="btn btn-danger" name="supprimer">Supprimer
						mon compte</button>
				</div>
				<div>
					<button name="Profil">Retour</button>
				</div>

			</div>

		</form>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>