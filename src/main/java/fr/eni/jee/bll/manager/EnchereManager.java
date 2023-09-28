package fr.eni.jee.bll.manager;

import java.util.List;

import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Enchere;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.dao.DAOEnchere;
import fr.eni.jee.dal.dao.DAOFactory;

public class EnchereManager {
	public static DAOEnchere dao;
//ajouter selectAll, validate à faire !!, 
	
    static {
        dao = DAOFactory.getDAOEnchere();
    }
    /**
	 * Create part of CRUD
	 */
    public void createEnchere(Enchere enchere, boolean dejaEncheri) throws DALException {
        dao.insert(enchere, dejaEncheri);
    }
    
    /**
     * @throws DALException 
     * 
     */
    public List<Enchere> selectAll() throws DALException{
    	return dao.selectAll();
    }
    /**
	 * Read part of CRUD
	 */
    public Enchere selectEnchereById(int id) throws DALException{
    	return dao.selectById(id);
    }

    public List<Integer> selectIdArticlesByUtilisateurAndEtat(Utilisateur utilisateur, String state) throws DALException {
        return dao.getNoArticlesByUtilisateurAndEtat(utilisateur, state);
    }

    public List<Integer> selectIdArticlesGagneByUtilisateur(Utilisateur utilisateur) throws DALException {
        return dao.getNoArticlesGagneByUtilisateur(utilisateur);
    }

    public List<String> getMontantEtPseudoEtIdBestOffer(Article article) throws DALException {
        return dao.getMontantEtPseudoEtIdBestOffer(article);
    }
}
