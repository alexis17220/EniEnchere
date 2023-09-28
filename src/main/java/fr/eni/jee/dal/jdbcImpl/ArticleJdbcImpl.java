package fr.eni.jee.dal.jdbcImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.bll.manager.EnchereManager;
import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Categorie;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.ConnectionProvider;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.ErrorCodesDAL;
import fr.eni.jee.dal.dao.DAOArticle;

public class ArticleJdbcImpl implements DAOArticle {

	private static String INSERT_ART = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String ALL_ART = "SELECT DISTINCT `no_article`,`nom_article`,`description`,`date_debut_encheres`,`date_fin_encheres`,`prix_initial`,`prix_vente`,art.`no_utilisateur`,art.`no_categorie`, `image` FROM `ARTICLES_VENDUS` AS art INNER JOIN CATEGORIES AS cat ON art.`no_categorie`= cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.`no_utilisateur` = user.no_Utilisateur";
	private static String ART ="SELECT DISTINCT * FROM `ARTICLES_VENDUS` AS art INNER JOIN CATEGORIES AS cat ON art.`no_categorie`= cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.`no_utilisateur` = user.no_Utilisateur WHERE art.no_article = ?";	
	private static String SELECT_CATEG_ARTICLE = "SELECT C.no_categorie, libelle FROM CATEGORIES C INNER JOIN ARTICLES_VENDUS A on A.no_categorie=C.no_categorie WHERE no_article=?;";
	private static String SELECT_UTILISATEUR_ARTICLE = "SELECT A.no_Utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit from UTILISATEURS U inner join ARTICLES_VENDUS A on U.no_Utilisateur = A.no_utilisateur where no_article=?;";
	
	private static String SELECT_ALL_CURRENT = "SELECT DISTINCT `no_article`,`nom_article`,`description`,`date_debut_encheres`,`date_fin_encheres`,`prix_initial`,`prix_vente`,art.`no_utilisateur`,art.`no_categorie`, `image` FROM `ARTICLES_VENDUS` AS art INNER JOIN CATEGORIES AS cat ON art.`no_categorie`= cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.`no_utilisateur` = user.no_Utilisateur WHERE date_debut_encheres <= CURRENT_TIMESTAMP AND date_fin_encheres >= CURRENT_TIMESTAMP";
	private static String SELECT_ALL_CURRENT_CONNECTE = "SELECT DISTINCT `no_article`,`nom_article`,`description`,`date_debut_encheres`,`date_fin_encheres`,`prix_initial`,`prix_vente`,art.`no_utilisateur`,art.`no_categorie`, `image` FROM `ARTICLES_VENDUS` AS art INNER JOIN CATEGORIES AS cat ON art.`no_categorie`= cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.`no_utilisateur` = user.no_Utilisateur WHERE art.no_utilisateur!=? AND date_debut_encheres <= CURRENT_TIMESTAMP AND date_fin_encheres >= CURRENT_TIMESTAMP";
	private static String SELECT_ALL_FINISHED = "SELECT DISTINCT `no_article`,`nom_article`,`description`,`date_debut_encheres`,`date_fin_encheres`,`prix_initial`,`prix_vente`,art.`no_utilisateur`,art.`no_categorie`, `image` FROM `ARTICLES_VENDUS` AS art INNER JOIN CATEGORIES AS cat ON art.`no_categorie`= cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.`no_utilisateur` = user.no_Utilisateur WHERE date_fin_encheres <= CURRENT_TIMESTAMP";
	private static String UPDATE_FINISHED = "UPDATE ARTICLES_VENDUS SET prix_vente=? where no_article=?";
	
	@Override
	public void insert(Article article) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();
		try (
				
				PreparedStatement pStmt = cnx.prepareStatement(INSERT_ART);) {
			// on change le ? de notre requ�te avec les informations saisie par user pass�
			// en argument
			pStmt.setString(1, article.getNom_article());
			pStmt.setString(2, article.getDescription());
			pStmt.setString(3, article.getDateDebutEncheres());
			pStmt.setString(4, article.getDateFinEncheres());
			pStmt.setInt(5, article.getPrix_initial());
			pStmt.setInt(6, article.getPrix_vente());
			pStmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(8, article.getCategorie().getNo_Categorie());
			pStmt.setString(9, article.getImage());

			// on envoie la requ�te
			pStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 DALException dalException = new DALException();
	         dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
	         throw dalException;
		}		
	}

	@Override
	public Article selectById(int id) throws DALException {
		// on utilise un bloc try-with-resources pour liberer automatiquement les
				// ressources connection / statements
				try (Connection cnx = ConnectionProvider.getConnection();
						PreparedStatement pStmt = cnx.prepareStatement(ART);) {
					// on change le ? de notre requ�te avec le numero utilisateur du utilisateur
					// pass� en argument
				
				pStmt.setInt(1, id);
				// on recupere dans un ResultSet le resultat de la requete SQL
				ResultSet rs = pStmt.executeQuery();

				// on renvoie le premier element de notre ResultSet (le seul normalement)
				if (rs.next()) {
					Article article = new Article(	
							rs.getInt("no_Article"),
							rs.getString("nom_article"),
							rs.getString("description"), 
							rs.getString("date_debut_encheres"),
							rs.getString("date_fin_encheres"), 
							rs.getInt("prix_initial"), 
							rs.getInt("prix_vente"),
							new Utilisateur (
							rs.getInt("no_utilisateur"),
							rs.getString("pseudo"), 
							rs.getString("nom"), 
							rs.getString("prenom"),
							rs.getString("email"), 
							rs.getString("telephone"), 
							rs.getString("rue"),
							rs.getString("code_postal"), 
							rs.getString("ville"), 
							rs.getString("mot_de_passe"),
							rs.getInt("credit"), 
							rs.getBoolean("administrateur")),
							new Categorie(
							rs.getInt("no_categorie"), 
							rs.getString("libelle")),
							rs.getString("image"));
					return article;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		Connection cnx = ConnectionProvider.getConnection();

		try (Statement stmt = cnx.createStatement();) {
			List<Article> article = new ArrayList<Article>();
			ResultSet rs = stmt.executeQuery(ALL_ART);
			while (rs.next()) {
				article.add(new Article(
						rs.getInt("no_Article"), 
						rs.getString("nom_article"),
						rs.getString("description"), 
						rs.getString("date_debut_encheres"),
						rs.getString("date_fin_encheres"), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"),
						new Utilisateur (
						rs.getString("pseudo"), 
						rs.getString("nom"), 
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("rue"),
						rs.getString("code_postal"), 
						rs.getString("ville"), 
						rs.getString("mot_de_passe"),
						rs.getInt("credit"), 
						rs.getBoolean("administrateur")),
						new Categorie(
						rs.getInt("no_categorie"), 
						rs.getString("libelle")),
						rs.getString("image")));
			}
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Article> getArticleEnCoursVente() {
		try(Connection cnx = ConnectionProvider.getConnection();
				Statement stmt = cnx.createStatement();){
			List<Article> encheresEnCours = new ArrayList<Article>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_CURRENT);
			while(rs.next()) {
				encheresEnCours.add(new Article(
						rs.getInt("no_Article"), 
						rs.getString("nom_article"),
						rs.getString("description"), 
						rs.getString("date_debut_encheres"),
						rs.getString("date_fin_encheres"), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"),
						null,
						null, 
						null));
			}
			return encheresEnCours;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	@Override
	public List<Article> getAllEncheresEnCoursC(Utilisateur u) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CURRENT_CONNECTE);){
			List<Article> encheresEnCours = new ArrayList<Article>();
			pstmt.setInt(1, u.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				encheresEnCours.add(new Article(
						rs.getInt("no_Article"), 
						rs.getString("nom_article"),
						rs.getString("description"), 
						rs.getString("date_debut_encheres"),
						rs.getString("date_fin_encheres"), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"),
						null,
						null, 
						null));
			}
			System.out.println(pstmt.toString());
			return encheresEnCours;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	@Override
	public List<Article> getAllEncheresFini() {
		try (Connection cnx = ConnectionProvider.getConnection();
				Statement stmt = cnx.createStatement();){
			List<Article> listArticleFini = new ArrayList<Article>();
			EnchereManager enchereMana = new EnchereManager();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_FINISHED);
			while(rs.next()) {
				PreparedStatement pStmt = cnx.prepareStatement(UPDATE_FINISHED);
				if (rs.getInt("prix_vente")==0) {
					pStmt.setInt(1,Integer.parseInt(enchereMana.getMontantEtPseudoEtIdBestOffer(new Article(
							rs.getInt("no_Article"), 
							rs.getString("nom_article"),
							rs.getString("description"), 
							rs.getString("date_debut_encheres"),
							rs.getString("date_fin_encheres"), 
							rs.getInt("prix_initial"), 
							rs.getInt("prix_vente"),
							null,
							null, 
							null)).get(0)));
					pStmt.setInt(2, rs.getInt("no_Article"));
					ResultSet rs2 = pStmt.executeQuery();
				}
				
			}
		}catch(SQLException e) {e.printStackTrace();} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (DALException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Categorie getCategorieArticle(Article article) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEG_ARTICLE)){
			Categorie lacateg = null;
			pStmt.setInt(1, article.getNoArticle());
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				lacateg = new Categorie(rs.getInt("C.no_categorie"), rs.getString("libelle"));
			}
			return lacateg;
		}catch(SQLException e) {e.printStackTrace();}                                    
		return null;
	}

	@Override
	public Utilisateur getCreateurArticle(Article article) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_UTILISATEUR_ARTICLE)){
			Utilisateur createur = null;
			pStmt.setInt(1, article.getNoArticle());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				createur = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), false);
			}
			return createur;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}

	@Override
	public void update(Article var) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub

	}
	

	
}


