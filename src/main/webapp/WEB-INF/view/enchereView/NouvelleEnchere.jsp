<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle Enchere</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
	<style type="text/css">
	li{
	  list-style-type:none;
	}
	</style>
</head>
<body class="container-fluid">
	<header>
		<section >
		
			<%@ include file="/../Header.jsp"%>
			
		</section>
	</header>
	<main>
		<section class="row">
		<h2 class="text-center">Nouvelle Vente</h2>
			<div class="col-lg-4">
				<div class="col-sm-6 col-md-4">
					<img class="figure-img img-fluid rounded" alt="TestImage"
						src="image/">

				</div>
			</div>
			<div class="col-lg-4">
				<form action="" method="POST">

					<input type="hidden" name="id" value="${nomUser.getNoUtilisateur()}" />
					<div class="row">

						 <div class="col-lg-12 text-center mb-3 mt-3"> 
						<label for="article" class="form-label text-center mt-1">Article
							: </label> <input type="text" class="form-control" id="article"
							name="article">
						 </div> 
						 <div class="col-lg-12 text-center mb-3 mt-3"> 
						<label for="article" class="form-label text-center mt-1">Description
							: </label><br>
						<textarea rows="" cols="100" class="form-control"
							name="description" maxlength="300"></textarea>

						 </div> 
						 <div class="col-lg-12 text-center mb-3 mt-3"> 
						<label for="article" class="form-label text-center mt-1">Catégorie
							: </label> <select name="categorie" class="form-control">
							<c:forEach items="${cat}" var="categorie">
								<option value="${categorie.getNo_Categorie()}">${categorie.getLibelle()}</option>
							</c:forEach>
						</select>
						 </div> 


						 <div class="col-lg-12 text-center mb-3 mt-3"> 
						<label for="photo" class="form-label text-center mt-1">Photo
							de l'article : </label> <input type="file" class="form-control"
							id="photo" name="photo">
						 </div> 
						 <div class="col-lg-12 text-center mb-3 mt-3"> 
						<label for="prix" class="form-label text-center mt-1">Mise
							a prix : </label> <input type="number" class="form-control" id="prix"
							name="prix">
						 </div> 
						 <div class="col-lg-6 text-center mb-3 mt-3"> 
						<label for="" class="form-label text-center mb-1">Début
							de l'enchere : </label> <input type="date" class="form-control" id="date"
							name="dateDeb" required="required">
						 </div> 
						 <div class="col-lg-6 text-center mb-3 mt-3"> 
						<label for="date" class="form-label text-center mb-1">Fin
							de l'enchere : </label> <input type="date" class="form-control" id="date"
							name="dateEnd" required="required">
						 </div> 
						<div class="card mt-5">
							<div class="card-header">
								<h3>Retrait</h3>
							</div>
							<div class="card-body">
								 <div class="col-lg-12 text-center mb-3 mt-3"> 
								<label for="rue" class="form-label text-center mt-1">Rue
									: </label> <input type="text" class="form-control" id="rue" name="rue"
									value="${nomUser.getRue()}">
								 </div> 
								 <div class="col-lg-12 text-center mb-3 mt-3"> 
								<label for="code" class="form-label text-center mt-1">Code
									Postal : </label> <input type="text" class="form-control" id="code"
									name="code" value="${nomUser.getCodePostal()}">
								 </div> 
								 <div class="col-lg-12 text-center mb-3 mt-3"> 
								<label for="ville" class="form-label text-center mt-1">Ville
									: </label> <input type="text" class="form-control" id="ville"
									name="ville" value="${nomUser.getVille()}">
								 </div> 
							</div>
						</div>
					</div>
					<div class="row mt-2">
						<div class="col-lg-4  text-center">
							<button type="submit" class="btn btn-success" name="save">Enregistrer</button>
						</div>
						<div class="col-lg-4  text-center">
							<button class="btn btn-primary" name="cancer">Annuler</button>
						</div>
						<!--  <div class="col-lg-4  text-center">
							<button class="btn btn-secondary" name="annulVente">Annuler
								la vente</button>
						</div>  -->
						
					</div>

				</form>

			</div>
			<div class="col-lg-4"></div>
		</section>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>