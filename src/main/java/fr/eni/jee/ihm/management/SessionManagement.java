package fr.eni.jee.ihm.management;

import fr.eni.jee.bll.manager.UtilisateurManager;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManagement {

	public static void setSessionConnected(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(300);
		session.setAttribute("isLogged", "true");
	}

	public static void setUtilisateurSession(HttpServletRequest request, String pseudo)
			throws DALException {
		UtilisateurManager um = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur utilisateurToBean = um.getUtilisateurByPseudo(pseudo);
		session.setAttribute("utilisateurSession", utilisateurToBean);
	}

	public static void destroySession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
}