
package fr.eni.jee.ihm.enchere;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.jee.bll.manager.ArticleManager;
import fr.eni.jee.bll.manager.EnchereManager;
import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Categorie;
import fr.eni.jee.bo.Enchere;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.ihm.management.SessionManagement;

/**
 * Servlet implementation class DetailsEnchere
 */
@WebServlet("/DetailsEnchere")
public class DetailsEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private EnchereManager enchereMana = new EnchereManager();
    private ArticleManager artMana = new ArticleManager();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			int idArticle;
			if(session.getAttribute("idArticleAsso")!=null) {
				idArticle =Integer.parseInt(String.valueOf(session.getAttribute("idArticleAsso")));
				session.removeAttribute("idArticleAsso");
			}else {
				idArticle = Integer.parseInt(request.getParameter("monArticle"));
				System.out.println("Allo???");
			}
			Article articleAsso = artMana.getArticleAtId(idArticle);
			Utilisateur createurAsso = articleAsso.getUtilisateur();
			Categorie categAsso = articleAsso.getCategorie();
			List<String> MaxNom = enchereMana.getMontantEtPseudoEtIdBestOffer(articleAsso);	
			boolean dejaEncherir;
			if(MaxNom.get(0)!=null) {
				dejaEncherir = true;
				request.setAttribute("meilleurOffre", MaxNom);
				request.setAttribute("dejaEncherir", dejaEncherir);
			}else {
				dejaEncherir = false;
				//request.setAttribute("false", dejaEncherir);
			}
			System.out.println(dejaEncherir);
			
			request.setAttribute("nomUser", session.getAttribute("utilisateurSession"));
			request.setAttribute("monArticle", articleAsso);
			request.setAttribute("maCateg", categAsso);
			request.setAttribute("createurArticle", createurAsso);
			request.setAttribute("nomUser", createurAsso);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/view/enchereView/DetailsEnchere.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("encherir")!=null) {
			try {
				HttpSession session = request.getSession();
				Article article = artMana.getArticleAtId(Integer.parseInt(request.getParameter("monArticle")));
				Utilisateur utilEnSession = (Utilisateur) session.getAttribute("utilisateurSession");
				System.out.println();
				Enchere enchereAcreer = new Enchere(new Date(System.currentTimeMillis()), Integer.parseInt(request.getParameter("prixEncherir")), utilEnSession, article);
				System.out.println(enchereAcreer.toString());
				List<String> MaxNom = enchereMana.getMontantEtPseudoEtIdBestOffer(article);	
				boolean dejaEncheri;
				if(MaxNom.get(0)!=null) {
					dejaEncheri = true;
				}else {
					dejaEncheri = false;
				}
				System.out.println("Deja encheri? " + dejaEncheri);
				enchereMana.createEnchere(new Enchere(new Date(System.currentTimeMillis()), Integer.parseInt(request.getParameter("prixEncherir")), utilEnSession, article),dejaEncheri);
			} catch (DALException e) {
				e.printStackTrace();
			}
			doGet(request, response);
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

		if(request.getParameter("Index")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/");
		}
		
		if(request.getParameter("Logout")!=null) {
			SessionManagement.destroySession(request);
			response.sendRedirect("/Eni-ProjetEnchere/");
		}
	}

}