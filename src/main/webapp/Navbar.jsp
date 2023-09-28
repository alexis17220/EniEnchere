<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<nav class="navbar navbar-expand-lg ">
	<div class="container-fluid">

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<img alt="Menu" src="./icone.png" width="50px" class="rounded">
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<form action="" method="POST">
				<ul>
					<li class="nav-item dropdown"><a
						class="nav-link " href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<img alt="Menu" src="./icone.png" width="50px" class="rounded">
					</a>
						<ul class="dropdown-menu text-center" aria-labelledby="navbarDropdown">
							<c:if
								test="${ empty nomUser.getPrenom() && empty nomUser.getNom() && empty nomUser.getPseudo() }">
								<li><button class="btn" name="Connexion">Connexion</button></li>
								<li><button class="btn " name="Inscription">Inscription</button></li>
							</c:if>

							<c:if
								test="${ !empty nomUser.getPrenom() || !empty nomUser.getNom() || !empty nomUser.getPseudo() && !empty nomUser.getCredit() }">
								<li><button class="btn " name="Profil">Profil</button></li>

								<li><button class="btn " name="NouvelleEnchere">Nouvelle
										Enchère</button></li>
								<li><button class="btn " name="MesEncheres">Mes
										Enchères</button></li>
								<li><button class="btn"
										name="EncheresRemportees">Mes Enchères Remportées</button></li>

								<li><button class="btn " name="Logout">Déconnexion</button></li>
							</c:if>
						</ul></li>

				</ul>



			</form>
		</div>
	</div>
</nav>




</html>