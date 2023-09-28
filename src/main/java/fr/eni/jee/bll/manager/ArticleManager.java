package fr.eni.jee.bll.manager;

import java.util.List;
import java.util.regex.Pattern;

import fr.eni.jee.bll.BLLException;
import fr.eni.jee.bll.ErrorCodesBLL;
import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Categorie;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.dao.DAOArticle;
import fr.eni.jee.dal.dao.DAOFactory;

public class ArticleManager {

	private static DAOArticle dao;

	static {
		dao = DAOFactory.getDAOArticle();
	}
	/**
	 * Create part of CRUD
	 */
	public void add(Article article) throws DALException, BLLException {
		BLLException bllException = validateArticle(article);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
		dao.insert(article);	
		}	
	}
	/**
	 * Read part of CRUD
	 */
	public List<Article> getAllArticle() throws DALException {
		return dao.selectAll();
	}
	
	/**
	 * 
	 */
	public Article getArticleAtId(int id) throws DALException{
		return dao.selectById(id);
	}
	/**
	 * 
	 */
	public List<Article> getAllEncheresEnCours()throws DALException{
		return dao.getArticleEnCoursVente();
	}
	/**
	 * 
	 */
	public List<Article> getAllEncheresEnCoursC(Utilisateur u)throws DALException{
		return dao.getAllEncheresEnCoursC(u);
	}
	
//tester code erreurs
	public Categorie getCategAssocie(Article article) throws BLLException {
		BLLException bllexception = validateArticle(article);
		if (bllexception.hasErrors()) {
			throw bllexception;
		}else {
			return dao.getCategorieArticle(article);
		}
	}	
//tester code erreur 
	public Utilisateur getCreateurArticle(Article article) throws BLLException {
		BLLException bllexception = validateArticle(article);
		if (bllexception.hasErrors()) {
			throw bllexception;
		}else {
			return dao.getCreateurArticle(article);
		}
	}	
	
	private BLLException validateArticle(Article article) {
		
		String nomValidationRegEx = "[A-Za-z]+";
		
		String descriptValidationRegEx = "[A-Za-z0-9]+";
		
		String priceValidationRegEx = "^0[1-9][0-9]{8}$";
		BLLException bllException = new BLLException();
		
//finir et améliorer les verifs !!
		if (article.getNom_article().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_ARTICLE);
		}
		if (!Pattern.matches(nomValidationRegEx, article.getNom_article())) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_ARTICLE);
		}
		if (article.getDescription().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_DESCRIPTION_ARTICLE);
		}
		if (!Pattern.matches(descriptValidationRegEx, article.getDescription())) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_DESCRIPTION_ARTICLE);
		}
		
		if(article.getPrix_initial()>0) {
		//	bllException.addError(ErrorCodesBLL.ERROR_PRICE_NOT_);
		}
		
		
		
		return bllException;
		}
	
}
