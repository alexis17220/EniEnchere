<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes Enchères</title>
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
<section >
		
			<%@ include file="/../Header.jsp"%>
			
		</section>
		<main>
		<div>
			<h2  class="text-center">Liste de mes encheres</h2>
			<form action="" method="POST">
				<div class="row text-center">
					<c:forEach var="uneEnchere" items="${listeEncheres}">
						<div class="col-lg-4 mt-2 mb-2 text-center">
							<ul>
								<li>
									<h3>${uneEnchere.getNom_article()}</h3> Fini le :
									${uneEnchere.getDateFinEncheres()}<br>
									${uneEnchere.getDescription()}
								</li>
							</ul>

							<button class="btn btn-primary mt-2 text-center"
								value="${uneEnchere.getNoArticle()}" name="detailEnchere">Detail
								Enchere</button>
						</div>
					</c:forEach>
				</div>
			</form>
		</div>
		</main>
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>