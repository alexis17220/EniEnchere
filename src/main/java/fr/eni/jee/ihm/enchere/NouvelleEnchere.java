package fr.eni.jee.ihm.enchere;

import java.io.IOException;
import java.sql.Timestamp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.jee.bll.manager.ArticleManager;
import fr.eni.jee.bll.manager.CategorieManager;
import fr.eni.jee.bll.manager.UtilisateurManager;
import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.ihm.management.SessionManagement;

/**
 * Servlet implementation class NouvelleEnchere
 */
@WebServlet("/NouvelleEnchere")
public class NouvelleEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategorieManager catmana = new CategorieManager();
	private ArticleManager art = new ArticleManager();
	private UtilisateurManager user = new UtilisateurManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		// Utilisateur en session
		HttpSession session = request.getSession();
		// Liste des encheres si connecte
		if(session.getAttribute("utilisateurSession")!=null) {
			request.setAttribute("nomUser", session.getAttribute("utilisateurSession"));}

		try {

			request.setAttribute("cat", catmana.getAllCategories());

		} catch (DALException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		request.getRequestDispatcher("/WEB-INF/view/enchereView/NouvelleEnchere.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
//		if(request.getParameter("Index")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/");
//		}
//		if(request.getParameter("Connexion")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/Connexion");
//		}
//		if(request.getParameter("Inscription")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/Inscription");
//		}
//		if(request.getParameter("Profil")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/Profil");
//		}
//		if(request.getParameter("NouvelleEnchere")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/NouvelleEnchere");
//		}
//		if(request.getParameter("MesEncheres")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/MesEncheres");
//		}
//		if(request.getParameter("EncheresRemportees")!=null) {
//			response.sendRedirect("/Eni-ProjetEnchere/MesEncheresRemportees");
//		}
//
//		if(request.getParameter("detailEnchere")!=null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("idArticleAsso", request.getParameter("detailEnchere"));
//			response.sendRedirect("/Eni-ProjetEnchere/DetailsEnchere");
//			/*System.out.println(request.getParameter("detailEnchere"));
//			doGet(request, response);*/
//		}
//		
//		if(request.getParameter("Logout")!=null) {
//			SessionManagement.destroySession(request);
//			response.sendRedirect("/Eni-ProjetEnchere/");
//		}
//		
		if (request.getParameter("save") != null) {

			request.setCharacterEncoding("UTF-8");
		

			try {

			
				Timestamp stmp = new Timestamp(System.currentTimeMillis());
				int heureActuelle = stmp.getHours();
				int minActuelle = stmp.getMinutes();
				int secActuelle = stmp.getSeconds();
				
				
				String dateDeb = request.getParameter("dateDeb") + " " +heureActuelle+":"+minActuelle+":"+secActuelle;
				System.out.println(dateDeb);
				String dateFin = request.getParameter("dateEnd") + " " +heureActuelle+":"+minActuelle+":"+secActuelle;
				
				// Date java.util a transvaser en java.sql
				Article article = new Article(request.getParameter("article"), request.getParameter("description"),
						dateDeb, dateFin, Integer.parseInt(request.getParameter("prix")),
						user.getUtilisateurById(Integer.parseInt(request.getParameter("id"))),
						catmana.getCategoriesById(Integer.parseInt(request.getParameter("categorie"))),
						request.getParameter("photo"));

				System.out.println(article.toString());

				art.add(article);

//				System.out.println(article.toString());
//
//				art.add(article);

			/*} catch (ParseException e2) {
// TODO Auto-generated catch block
				e2.printStackTrace();*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		response.sendRedirect("./");
		
		
	}

}