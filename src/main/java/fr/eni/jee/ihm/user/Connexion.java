package fr.eni.jee.ihm.user;

//import fr.eni.jee.bll.manager.UtilisateurManager;
//import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.ihm.management.SessionManagement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("/WEB-INF/view/userView/Connexion.jsp").forward(request, response);
	}

//Verifier nature de l'identifiant email/pseudo par requete 

//Verif	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("login") != null) {
			SessionManagement.setSessionConnected(request);
			try {
				// Propager nom du User
				
				SessionManagement.setUtilisateurSession(request, request.getParameter("identifiant"));
			} catch (DALException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			response.sendRedirect("/Eni-ProjetEnchere");}
//		} else if (request.isUserInRole("user")) {
//			// authentication sucess !
//			SessionManagement.setSessionConnected(request);
//			try {
//				// Propager nom du User
//				SessionManagement.setUtilisateurSession(request, request.getUserPrincipal().getName());
//			} catch (DALException e) {
//				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			}
//			response.sendRedirect("/ProjetEnchere/Accueil");
//		}
		if (request.getParameter("NewU") != null){
				response.sendRedirect("/Inscription");
		}
	}

}