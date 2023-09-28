package fr.eni.jee.dal.dao;

import fr.eni.jee.dal.jdbcImpl.*;

public class DAOFactory {
	/**
	 * Return l'interface pour Couplage Faible
	 * @return
	 */
    public static DAOUtilisateur getDAOUtilisateur() {
        return new UtilisateurJdbcImpl();
    }
    
    public static DAOArticle getDAOArticle() {
        return (DAOArticle) new ArticleJdbcImpl();
    }
    
    public static DAOCategorie getDAOCategorie() {
        return (DAOCategorie) new CategorieJdbcImpl();
    }
    public static DAOEnchere getDAOEnchere() {
        return (DAOEnchere) new EnchereJdbcImpl();
    }  

}