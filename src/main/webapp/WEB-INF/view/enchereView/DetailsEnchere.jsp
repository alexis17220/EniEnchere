<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details Enchères</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
	li{
	  list-style-type:none;
	}
	</style>
</head>
<body class="container-fluid">
	<section >
		
			<%@ include file="/../Header.jsp"%>
			
		</section>

	<div id="blockGlobal">
		<div id="photo"></div>
		<div class="text-center">
			<h2 >Détail vente</h2>
			<br /> <span>${monArticle.getNom_article()}</span><br /> <span>Description
				: ${monArticle.getDescription()}</span><br /> <span>Catégorie :
				${maCateg.getLibelle()}</span><br />


			<c:choose>
				<c:when test="${dejaEncherir}">
					<span>Meilleure offre : ${meilleurOffre.get(0)} pts par
						${meilleurOffre.get(1)}</span>
					<br />
					<input type="hidden" value="QSD" name="dejaEncheri" />
				</c:when>
				<c:otherwise>
					<span>Meilleure offre : Pas encore d'offre.</span>
					<br />
				</c:otherwise>
			</c:choose>


			<span>Mise a prix : ${monArticle.getPrix_initial()}</span><br /> <span>Fin
				de l'enchere : ${monArticle.getDateFinEncheres()}</span><br /> 
				<span>Retrait :<br>
				 CP : ${nomUser.getCodePostal()} Ville : ${nomUser.getVille()}</span> <br /> <span>Vendeur :
				${createurArticle.getPseudo()}</span><br />
			<div>
			<c:if
				test="${ !empty nomUser.getPrenom() || !empty nomUser.getNom() || !empty nomUser.getPseudo() && !empty nomUser.getCredit() }">
				<form action="" method="POST">
					<span>Ma proposition :</span>
					<c:choose>
						<c:when test="${dejaEncherir}">
							<input type="number" name="prixEncherir"
								max="${nomUser.getCredit()}" min="${meilleurOffre.get(0)+1}"
								value="0" />
						</c:when>
						<c:otherwise>
							<input type="number" name="prixEncherir"
								max="${nomUser.getCredit()}"
								min="${monArticle.getPrix_initial()+1}" value="0" />
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="monArticle"
						value="${monArticle.getNoArticle()}" />
					<button name="encherir">Enchérir</button>
				</form>
				</c:if>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>