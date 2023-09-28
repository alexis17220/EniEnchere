-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--


CREATE DATABASE projetEnchere;



--USE projetEnchere;
/*DROP TABLE ENCHERES;
DROP TABLE RETRAITS;
DROP TABLE CATEGORIES;
DROP TABLE ARTICLES_VENDUS;
DROP TABLE UTILISATEURS;*/



CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

  

CREATE TABLE UTILISATEURS (
    no_Utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(50) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_Utilisateur)


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_Utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
)



ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)


CREATE TABLE RETRAITS (
	no_article       INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article)
ALTER TABLE RETRAITS
    ADD CONSTRAINT retrait_article_fk FOREIGN KEY ( no_article ) REFERENCES  ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
    ON UPDATE no action 


CREATE TABLE ENCHERES(	
	no_enchere  INTEGER IDENTITY(1,1) NOT NULL,
	date_enchere datetime NOT NULL,
	montant_enchere INTEGER NOT NULL,
	no_article INTEGER NOT NULL,
	no_Utilisateur INTEGER NOT NULL
 )

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY ( no_enchere)
 
ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_Utilisateur ) REFERENCES UTILISATEURS ( no_Utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_no_article_fk FOREIGN KEY ( no_article ) REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 
	

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_Utilisateur )
        REFERENCES utilisateurs ( no_Utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 


