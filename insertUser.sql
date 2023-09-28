USE [projetEnchere]
GO

INSERT INTO [dbo].[UTILISATEURS]
           ([pseudo]
           ,[nom]
           ,[prenom]
           ,[email]
           ,[telephone]
           ,[rue]
           ,[code_postal]
           ,[ville]
           ,[mot_de_passe]
           ,[credit]
           ,[administrateur])
     VALUES
           ('test'
           ,'essai'
           ,'base'
           ,'testing@campus-enbi.fr'
           ,'0123456789'
           ,'13 rue des macarons'
           ,'79541'
           ,'Niort'
           ,'123456'
           ,'123'
           ,'1')
GO


