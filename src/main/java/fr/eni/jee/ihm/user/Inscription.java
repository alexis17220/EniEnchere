package fr.eni.jee.ihm.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.jee.bll.BLLException;
import fr.eni.jee.bll.manager.UtilisateurManager;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.ihm.management.PasswordManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager um = new UtilisateurManager();
		List<String> errors = new ArrayList<>();
		// Hash password
		String password = request.getParameter("password");
		String generatedPassword = PasswordManagement.hashPassword(password);
		// New user
		Utilisateur utilisateur = new Utilisateur(
				request.getParameter("pseudo"), 
				request.getParameter("nom"),
				request.getParameter("prenom"), 
				request.getParameter("email"), 
				request.getParameter("telephone"),
				request.getParameter("rue"), 
				request.getParameter("codepostal"), 
				request.getParameter("ville"),
				generatedPassword, 
				0, 
				false);
		try {
			System.out.println(utilisateur.toString());
			um.createUtilisateur(utilisateur);
		} catch (BLLException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Utilisateur en session
		HttpSession session = request.getSession();
		// Liste des encheres si connecte
		if(session.getAttribute("utilisateurSession")!=null) {
			request.setAttribute("nomUser", session.getAttribute("utilisateurSession"));}
		
	
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		response.sendRedirect("/Eni-ProjetEnchere");
//		try {
//			um.createUtilisateur(utilisateur);
//		} catch (BLLException e) {
//		} catch (DALException e) {
//		}
//		if (errors.isEmpty()) {
//			request.setAttribute("loginCreated", "true");
//			request.setAttribute("page", "home");
//		} else {
//			request.setAttribute("page", "createLogin");
//			request.setAttribute("utilisateurError", utilisateur);
//		}
//		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/userView/Inscription.jsp");
		request.setAttribute("page", "createLogin");
		rd.forward(request, response);
	}
}