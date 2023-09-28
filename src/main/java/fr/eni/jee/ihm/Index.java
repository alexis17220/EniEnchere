
package fr.eni.jee.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import fr.eni.jee.bll.manager.ArticleManager;
import fr.eni.jee.bll.manager.EnchereManager;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.ihm.management.SessionManagement;


/**
 * Servlet implementation class Index
 */
@WebServlet("")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ArticleManager articleMana = new ArticleManager();

       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Utilisateur en session
		HttpSession session = request.getSession();
		// Liste des encheres si connecte
		if(session.getAttribute("utilisateurSession")!=null) {
			request.setAttribute("nomUser", session.getAttribute("utilisateurSession"));
		
			try {
				request.setAttribute("listeEncheres", articleMana.getAllEncheresEnCoursC((Utilisateur) session.getAttribute("utilisateurSession")));	
			} catch (DALException e) {
				e.printStackTrace();
			}
			//Si non connecté
		}else {
			try {
				request.setAttribute("listeEncheres", articleMana.getAllEncheresEnCours());	
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Connexion")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/Connexion");
		}
		if(request.getParameter("Inscription")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/Inscription");
		}
		if(request.getParameter("Profil")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/Profil");
		}
		if(request.getParameter("NouvelleEnchere")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/NouvelleEnchere");
		}
		if(request.getParameter("MesEncheres")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/MesEncheres");
		}
		if(request.getParameter("EncheresRemportees")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/MesEncheresRemportees");
		}

		if(request.getParameter("detailEnchere")!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("idArticleAsso", request.getParameter("detailEnchere"));
			response.sendRedirect("/Eni-ProjetEnchere/DetailsEnchere");
			/*System.out.println(request.getParameter("detailEnchere"));
			doGet(request, response);*/
		}
		
		if(request.getParameter("Logout")!=null) {
			SessionManagement.destroySession(request);
			response.sendRedirect("/Eni-ProjetEnchere/");
		}
		
	
	}

}