package fr.eni.jee.bo;

import java.sql.Date;

public class Enchere {
	public int idEnchere;
	public Date dateEnchere;
	public int montantEnchere;
	public Utilisateur unParticipant;
	public Article articleConcerner;
	
	public Enchere(Date dateEnchere, int montant_enchere, Utilisateur unParticipant, Article articleConcerner) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montant_enchere;
		this.unParticipant = unParticipant;
		this.articleConcerner = articleConcerner;
	}

	public Enchere(int idEnchere, Date dateEnchere, int montantEnchere, Utilisateur unParticipant,
			Article articleConcerner) {
		super();
		this.idEnchere = idEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.unParticipant = unParticipant;
		this.articleConcerner = articleConcerner;
	}


	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	
	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montant_enchere) {
		this.montantEnchere = montant_enchere;
	}

	public Utilisateur getUnParticipant() {
		return unParticipant;
	}

	public void setUnParticipant(Utilisateur unParticipant) {
		this.unParticipant = unParticipant;
	}

	public Article getArticleConcerner() {
		return articleConcerner;
	}

	public void setArticleConcerner(Article articleConcerner) {
		this.articleConcerner = articleConcerner;
	}
	
	public int getIdEnchere() {
		return idEnchere;
	}

	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchère=" + dateEnchere + ", montant_enchere=" + montantEnchere + ", unParticipant="
				+ unParticipant + ", articleConcerner=" + articleConcerner + "]";
	}		
}
