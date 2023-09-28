package fr.eni.jee.bo;

import java.io.Serializable;

public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int noArticle;
	private String nom_article;
	private String description;
	private String dateDebutEncheres;
	private String dateFinEncheres;
	private int prix_initial;
	private int prix_vente;
	private Utilisateur Utilisateur;
	private Categorie Categorie;
	private String image;

	/**
	 * constructeur
	 * 
	 * @param noArticle
	 * @param nom_article
	 * @param description
	 * @param string
	 * @param string2
	 * @param prix_initial
	 * @param prix_vente
	 * @param no_Utilisateur
	 * @param no_categorie
	 */

	public Article(int noArticle, String nom_article, String description, String dateDebutEncheres, String dateFinEncheres,
			int prix_initial, int prix_vente, Utilisateur Utilisateur, Categorie no_categorie, String image) {
		super();
		this.noArticle = noArticle;
		this.nom_article = nom_article;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.Utilisateur = Utilisateur;
		this.Categorie = no_categorie;
		this.image = image;
	}

	/**
	 * constructeur
	 * 
	 * @param nom_article
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prix_initial
	 * @param prix_vente
	 * @param no_Utilisateur
	 * @param no_categorie
	 */

	public Article(String nom_article, String description, String dateDebutEncheres, String dateFinEncheres,
			int prix_initial, int prix_vente, Utilisateur Utilisateur, Categorie no_categorie, String image) {
		super();
		this.nom_article = nom_article;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.Utilisateur = Utilisateur;
		this.Categorie = no_categorie;
		this.image = image;
	}

	public Article(String nom_article, String description, String dateDeb, String dateFin,
			int prix_initial, Utilisateur Utilisateur, Categorie no_categorie, String image) {
		super();
		this.nom_article = nom_article;
		this.description = description;
		this.dateDebutEncheres = dateDeb;
		this.dateFinEncheres = dateFin;
		this.prix_initial = prix_initial;
		this.Utilisateur = Utilisateur;
		this.Categorie = no_categorie;
		this.image = image;

	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public String getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(String dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	public int getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public void setUtilisateur(Utilisateur no_Utilisateur) {
		this.Utilisateur = no_Utilisateur;
	}

	public Categorie getCategorie() {
		return Categorie;
	}

	public void setCategorie(Categorie no_categorie) {
		this.Categorie = no_categorie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ","
				+ " nom_article=" + nom_article + ","
				+ " description=" + description + ","
				+ " dateDebutEncheres=" + dateDebutEncheres + ","
				+ " dateFinEncheres=" + dateFinEncheres + ","
				+ " prix_initial=" + prix_initial + ","
				+ " prix_vente=" + prix_vente + ","
				+ " no_Utilisateur=" + Utilisateur + ","
				+ " no_categorie=" + Categorie.toString() + ","
				+ "image=" + image + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noArticle;
		return result;
	}
}
