package fr.eni.jee.ihm.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.jee.bll.manager.UtilisateurManager;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.ihm.management.SessionManagement;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilmana = new UtilisateurManager();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Utilisateur en session
				HttpSession session = request.getSession();
				// Liste des encheres si connecte
				if(session.getAttribute("utilisateurSession")!=null) {
					request.setAttribute("nomUser", session.getAttribute("utilisateurSession"));}
				
	
		request.getRequestDispatcher("/WEB-INF/view/userView/Profil.jsp").forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("modif")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/Profil/ModifProfil");
		}
		if(request.getParameter("Index")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/");
		}
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
