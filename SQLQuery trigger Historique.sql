create table histoUtilisateur(
	no_Utilisateur int primary key,
	pseudo varchar(30),
	nom varchar(30),
	prenom varchar(30),
	email varchar(50),
	telephone varchar(30),
	rue varchar(30),
	code_postal varchar(30),
	ville varchar(30),
	mot_de_passe varchar(30),
	credit int,
	administrateur bit,
)

create Trigger histoUtilisateurTrigger
on UTILISATEURS
after delete 
as 
declare
	@no_Utilisateur int,
	@pseudo varchar(30),
	@nom varchar(30),
	@prenom varchar(30),
	@email varchar(50),
	@telephone varchar(30),
	@rue varchar(30),
	@code_postal varchar(30),
	@ville varchar(30),
	@mot_de_passe varchar(30),
	@credit int,
	@administrateur bit;
begin
	select @no_Utilisateur = no_Utilisateur, @pseudo = pseudo, @nom = nom, @prenom = prenom, @email = email, @telephone = telephone, @rue = rue, @code_postal = code_postal, @ville = ville, @mot_de_passe = mot_de_passe, @credit = credit, @administrateur = administrateur from deleted;
	insert into histoUtilisateur values (@no_Utilisateur,@pseudo,@nom,@prenom,@email,@telephone,@rue,@code_postal,@ville,@mot_de_passe,@credit,@administrateur);
end