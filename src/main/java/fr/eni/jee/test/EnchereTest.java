package fr.eni.jee.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.eni.jee.bll.manager.EnchereManager;
import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Enchere;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;

class EnchereTest {

	@Test
	void testEnchereMax() {
		Article articleConcerne = new Article(1, "Chaise", null, null, null, 100, 0, null, null, null);
		Utilisateur util1 = new Utilisateur(1,"User1", null, null, null, null, null, null, null, null, 150, false);
		Utilisateur util2 = new Utilisateur(2,"User2", null, null, null, null, null, null, null, null, 150, false);
		Enchere premiereEnchere = new Enchere(null, 132, util1 , articleConcerne);
		Enchere deuxiemeEnchere = new Enchere(null, 150, util2 , articleConcerne);
		
		EnchereManager enchereMana = new EnchereManager();
		
		String pseudoUser = null;
		String qteMax = null;
		try {
			pseudoUser = enchereMana.getMontantEtPseudoEtIdBestOffer(articleConcerne).get(1);
			qteMax = enchereMana.getMontantEtPseudoEtIdBestOffer(articleConcerne).get(0);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("User2", pseudoUser);
		assertEquals("150", qteMax);
	}

}
