
package fr.eni.jee.ihm.enchere;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.jee.bll.manager.ArticleManager;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;

/**
 * Servlet implementation class MesEncheres
 */
@WebServlet("/MesEncheres")
public class MesEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArticleManager articleMana = new ArticleManager();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesEncheres() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	HttpSession session = request.getSession();
	
		if(session.getAttribute("utilisateurSession")!=null) {
			request.setAttribute("nomUser", session.getAttribute("utilisateurSession"));
		
			try {
				request.setAttribute("listeEncheres", articleMana.getAllEncheresEnCoursC((Utilisateur) session.getAttribute("utilisateurSession")));	
			} catch (DALException e) {
				e.printStackTrace();
			}
		}*/
		request.getRequestDispatcher("/WEB-INF/view/enchereView/MesEncheres.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("modif")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/ModifEnchere");
		}
		if(request.getParameter("detail")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/DetailsEnchere");
		}
	}

}