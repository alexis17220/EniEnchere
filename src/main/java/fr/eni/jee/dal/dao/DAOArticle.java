package fr.eni.jee.dal.dao;

import java.util.List;

import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Categorie;
import fr.eni.jee.bo.Utilisateur;

public interface DAOArticle extends DAO<Article> {
	List<Article> getArticleEnCoursVente();
	List<Article> getAllEncheresEnCoursC(Utilisateur u);
	List<Article> getAllEncheresFini();
	Categorie getCategorieArticle(Article article);
	Utilisateur getCreateurArticle(Article article);
	
}
