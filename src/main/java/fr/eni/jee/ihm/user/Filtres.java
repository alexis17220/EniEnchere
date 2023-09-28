package fr.eni.jee.ihm.user;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns = { 
				"/Profil/", 
				"/ModifProfil", 
				"/MesEncheres/", 
				"/MesEncheresRemportees/", 
				"/ModifEnchere/",
				"/NouvelleEnchere/" 
				}, 
		dispatcherTypes = { 
				DispatcherType.ERROR, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE,
				DispatcherType.REQUEST 
			}
		)
public class Filtres implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		if (httpRequest.isUserInRole("isLogged")){
			chain.doFilter(req, resp);
		}else {
            // On interdit l'acces
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            HttpSession session = httpRequest.getSession();
            // Redirect to the page were the user were
            if (httpRequest.getQueryString() != null) {
            	session.setAttribute("uriAndParamsRequested", httpRequest.getRequestURI() + "?" + httpRequest.getQueryString());
            } else {
                session.setAttribute("uriAndParamsRequested", httpRequest.getRequestURI());
            }
            // Afficher message d'erreur 
            session.setAttribute("mustBeLogged", "true");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
		}
	}
	
	public void init(FilterConfig config) throws ServletException {
    }
}

