package fr.eni.jee.dal.dao;

import java.util.List;

import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Enchere;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;

public interface DAOEnchere extends DAO<Enchere>{
	List<Integer> getNoArticlesByUtilisateurAndEtat(Utilisateur utilisateur, String state) throws DALException;
    List<Integer> getNoArticlesGagneByUtilisateur(Utilisateur utilisateur) throws DALException;
    List<String> getMontantEtPseudoEtIdBestOffer(Article article) throws DALException;
    List<Enchere> selectAll(int idA) throws DALException;
	void insert(Enchere enchere, boolean dejaencheri) throws DALException;

}
