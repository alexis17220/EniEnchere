
package fr.eni.jee.ihm.user;

import java.io.IOException;
import java.sql.SQLException;

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
import fr.eni.jee.ihm.management.SessionManagement;

/**
 * Servlet implementation class ModifProfil
 */
@WebServlet("/Profil/ModifProfil")
public class ModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilmana = new UtilisateurManager();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("allo? profil");
		Utilisateur util = (Utilisateur) session.getAttribute("utilisateurSession"); 
		try {
			
			request.setAttribute("nomUser", utilmana.getUtilisateurById(util.getNoUtilisateur()));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/userView/ModifProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("modifier")!=null) {
			doGet(request, response);
		}
		if (request.getParameter("supprimer")!=null) {
			
			Utilisateur util = (Utilisateur) session.getAttribute("utilisateurSession");
			System.out.println("OK1");
			try {
				System.out.println("OK2");
				utilmana.deleteUtilisateur(util.getNoUtilisateur());
				SessionManagement.destroySession(request);

				response.sendRedirect("/Eni-ProjetEnchere/");
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   	
		}
		if (request.getParameter("save")!=null) {
			boolean admin = false;
			String nmdp;
			
			if(request.getParameter("admin").equals("true")) {
				admin = true;
			}
			Utilisateur util = (Utilisateur) session.getAttribute("user");
			if(request.getParameter("mdp").equals("")) {
				nmdp = util.getMotDePasse();
			}else {
				nmdp = request.getParameter("mdp");
			}
			Utilisateur newUtil = new Utilisateur(Integer.parseInt(request.getParameter("id")), request.getParameter("pseudo"), request.getParameter("nom"), 
					request.getParameter("prenom"), request.getParameter("mail"), request.getParameter("tel"), request.getParameter("rue"), 
					request.getParameter("code"), request.getParameter("ville"), nmdp,Integer.parseInt(request.getParameter("credit")), admin);
			try {
				System.out.println(newUtil.toString());
				utilmana.updateUtilisateur(newUtil,true,false);
				session.setAttribute("user", newUtil);
				response.sendRedirect("/Eni-ProjetEnchere/Profil");
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		if(request.getParameter("Profil")!=null) {
			response.sendRedirect("/Eni-ProjetEnchere/Profil");
		}
		
		

	}

}