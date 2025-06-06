USE [ENCHERES_DB]
GO
/****** Object:  Table [dbo].[ADRESSES]    Script Date: 11/04/2025 11:25:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ADRESSES](
	[no_adresse] [int] IDENTITY(1,1) NOT NULL,
	[rue] [varchar](100) NOT NULL,
	[code_postal] [varchar](10) NOT NULL,
	[ville] [varchar](50) NOT NULL,
	[adresse_eni] [bit] NOT NULL,
 CONSTRAINT [adresse_pk] PRIMARY KEY CLUSTERED 
(
	[no_adresse] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ARTICLES_A_VENDRE]    Script Date: 11/04/2025 11:25:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ARTICLES_A_VENDRE](
	[no_article] [int] IDENTITY(1,1) NOT NULL,
	[nom_article] [varchar](30) NOT NULL,
	[description] [varchar](300) NOT NULL,
	[photo] [int] NULL,
	[date_debut_encheres] [date] NOT NULL,
	[date_fin_encheres] [date] NOT NULL,
	[statut_enchere] [int] NOT NULL,
	[prix_initial] [int] NOT NULL,
	[prix_vente] [int] NULL,
	[id_utilisateur] [varchar](30) NOT NULL,
	[no_categorie] [int] NOT NULL,
	[no_adresse_retrait] [int] NOT NULL,
 CONSTRAINT [articles_vendus_pk] PRIMARY KEY CLUSTERED 
(
	[no_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CATEGORIES]    Script Date: 11/04/2025 11:25:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CATEGORIES](
	[no_categorie] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](30) NOT NULL,
 CONSTRAINT [categorie_pk] PRIMARY KEY CLUSTERED 
(
	[no_categorie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ENCHERES]    Script Date: 11/04/2025 11:25:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ENCHERES](
	[id_utilisateur] [varchar](30) NOT NULL,
	[no_article] [int] NOT NULL,
	[montant_enchere] [int] NOT NULL,
	[date_enchere] [datetime] NOT NULL,
 CONSTRAINT [enchere_pk] PRIMARY KEY CLUSTERED 
(
	[id_utilisateur] ASC,
	[no_article] ASC,
	[montant_enchere] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ROLES]    Script Date: 11/04/2025 11:25:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ROLES](
	[ROLE] [nvarchar](50) NOT NULL,
	[IS_ADMIN] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ROLE] ASC,
	[IS_ADMIN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UTILISATEURS]    Script Date: 11/04/2025 11:25:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UTILISATEURS](
	[pseudo] [varchar](30) NOT NULL,
	[nom] [varchar](40) NOT NULL,
	[prenom] [varchar](50) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[telephone] [varchar](15) NULL,
	[mot_de_passe] [varchar](68) NOT NULL,
	[credit] [int] NOT NULL,
	[administrateur] [bit] NOT NULL,
	[no_adresse] [int] NOT NULL,
 CONSTRAINT [utilisateur_pk] PRIMARY KEY CLUSTERED 
(
	[pseudo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ADRESSES] ON 

INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (1, N'2B RUE BENJAMIN FRANKLIN', N'44800', N'SAINT HERBLAIN', 1)
INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (2, N'3 RUE MICKAËL FARADAY', N'44800', N'SAINT HERBLAIN', 1)
INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (3, N'8 RUE LÉO LAGRANGE', N'35131', N'CHARTRES DE BRETAGNE', 1)
INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (4, N'2 RUE GEORGES PERROS', N'29000', N'QUIMPER', 1)
INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (5, N'19 AVENUE LÉO LAGRANGE', N'79000', N'NIORT', 1)
INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (6, N'toto la rue', N'830000', N'totoland', 0)
INSERT [dbo].[ADRESSES] ([no_adresse], [rue], [code_postal], [ville], [adresse_eni]) VALUES (7, N'j''ai pas d''adresse', N'67100', N'Strasbourt', 0)
SET IDENTITY_INSERT [dbo].[ADRESSES] OFF
GO
SET IDENTITY_INSERT [dbo].[ARTICLES_A_VENDRE] ON 

INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (13, N'Enchere ouverte', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'coach_toto', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (14, N'Enchere en cours totorito', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'coach_toto', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (17, N'Enchere win par totorito', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 2, 3, NULL, N'coach_toto', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (18, N'Vente en cours by totorito', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'totorito', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (20, N'Vente terminé totorito2', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 2, 3, NULL, N'totorito', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (24, N'Vêtement (cours)', N'Vêtement', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'coach_toto', 4, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (25, N'Informatique (cours)', N'Informatique', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'coach_toto', 2, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (26, N'Sport & Loisir (cours)', N'Sport & Loisir', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'coach_toto', 3, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (27, N'Ameublement (cours)', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 1, 3, NULL, N'coach_toto', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (30, N'Vente livré totorito', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 3, 3, NULL, N'totorito', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (33, N'Enchere Win&Livré par totorito', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 3, 3, NULL, N'coach_toto', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (34, N'Vente terminé totorito1', N'Bague en plastoc', NULL, CAST(N'2025-03-30' AS Date), CAST(N'2025-04-20' AS Date), 2, 3, NULL, N'totorito', 1, 2)
INSERT [dbo].[ARTICLES_A_VENDRE] ([no_article], [nom_article], [description], [photo], [date_debut_encheres], [date_fin_encheres], [statut_enchere], [prix_initial], [prix_vente], [id_utilisateur], [no_categorie], [no_adresse_retrait]) VALUES (35, N'Vente non ouverte totorito', N'Vente non ouverte totorito', NULL, CAST(N'2025-04-26' AS Date), CAST(N'2025-04-30' AS Date), 0, 3, NULL, N'totorito', 1, 3)
SET IDENTITY_INSERT [dbo].[ARTICLES_A_VENDRE] OFF
GO
SET IDENTITY_INSERT [dbo].[CATEGORIES] ON 

INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (1, N'Ameublement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (2, N'Informatique')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (3, N'Sport&Loisir')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (4, N'Vêtement')
SET IDENTITY_INSERT [dbo].[CATEGORIES] OFF
GO
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'coach_titi', 17, 38, CAST(N'2025-04-11T10:00:45.957' AS DateTime))
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'coach_titi', 18, 15, CAST(N'2025-04-11T10:00:45.957' AS DateTime))
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'coach_titi', 20, 23, CAST(N'2025-04-11T10:00:45.957' AS DateTime))
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'coach_toto', 30, 16, CAST(N'2025-04-11T10:00:45.957' AS DateTime))
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'totorito', 13, 4, CAST(N'2025-04-11T10:48:02.150' AS DateTime))
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'totorito', 17, 40, CAST(N'2025-04-11T10:00:45.957' AS DateTime))
INSERT [dbo].[ENCHERES] ([id_utilisateur], [no_article], [montant_enchere], [date_enchere]) VALUES (N'totorito', 33, 17, CAST(N'2025-04-11T10:00:45.957' AS DateTime))
GO
INSERT [dbo].[ROLES] ([ROLE], [IS_ADMIN]) VALUES (N'ROLE_ADMIN', 1)
INSERT [dbo].[ROLES] ([ROLE], [IS_ADMIN]) VALUES (N'ROLE_USER', 0)
GO
INSERT [dbo].[UTILISATEURS] ([pseudo], [nom], [prenom], [email], [telephone], [mot_de_passe], [credit], [administrateur], [no_adresse]) VALUES (N'CeceLaBaie', N'Breuleux', N'Cédric', N'cedricbreuleux@gmail.com', N'0651504982', N'{bcrypt}$2a$10$UpZcJO2dWQ5FMsny4/kjLOj56bp6ap28HXT95eKIh1XQUEDYWbSrq', 1000, 0, 7)
INSERT [dbo].[UTILISATEURS] ([pseudo], [nom], [prenom], [email], [telephone], [mot_de_passe], [credit], [administrateur], [no_adresse]) VALUES (N'coach_admin', N'COACH', N'Eni', N'coach@campus-eni.fr', NULL, N'{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu', 10, 1, 1)
INSERT [dbo].[UTILISATEURS] ([pseudo], [nom], [prenom], [email], [telephone], [mot_de_passe], [credit], [administrateur], [no_adresse]) VALUES (N'coach_tata', N'Tata', N'Eni', N'tata@campus-eni.fr', NULL, N'{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu', 10, 0, 2)
INSERT [dbo].[UTILISATEURS] ([pseudo], [nom], [prenom], [email], [telephone], [mot_de_passe], [credit], [administrateur], [no_adresse]) VALUES (N'coach_titi', N'Titi', N'Eni', N'titi@campus-eni.fr', NULL, N'{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu', 10, 0, 3)
INSERT [dbo].[UTILISATEURS] ([pseudo], [nom], [prenom], [email], [telephone], [mot_de_passe], [credit], [administrateur], [no_adresse]) VALUES (N'coach_toto', N'Toto', N'Eni', N'toto@campus-eni.fr', NULL, N'{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu', 10, 0, 2)
INSERT [dbo].[UTILISATEURS] ([pseudo], [nom], [prenom], [email], [telephone], [mot_de_passe], [credit], [administrateur], [no_adresse]) VALUES (N'totorito', N'totorito', N'toto', N'toto@gmail.com', N'0651504982', N'{bcrypt}$2a$10$HhdOodvfRPRI8jcauwK03.qGFfkOzRf5CNb.E4DG.tn/oOjNCIV3y', 100, 0, 6)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [uc_categories]    Script Date: 11/04/2025 11:25:09 ******/
ALTER TABLE [dbo].[CATEGORIES] ADD  CONSTRAINT [uc_categories] UNIQUE NONCLUSTERED 
(
	[libelle] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [uc_utilisateurs]    Script Date: 11/04/2025 11:25:09 ******/
ALTER TABLE [dbo].[UTILISATEURS] ADD  CONSTRAINT [uc_utilisateurs] UNIQUE NONCLUSTERED 
(
	[pseudo] ASC,
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ADRESSES] ADD  DEFAULT ((0)) FOR [adresse_eni]
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE] ADD  DEFAULT ((0)) FOR [statut_enchere]
GO
ALTER TABLE [dbo].[UTILISATEURS] ADD  DEFAULT ((10)) FOR [credit]
GO
ALTER TABLE [dbo].[UTILISATEURS] ADD  DEFAULT ((0)) FOR [administrateur]
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE]  WITH CHECK ADD  CONSTRAINT [articles_vendus_categories_fk] FOREIGN KEY([no_categorie])
REFERENCES [dbo].[CATEGORIES] ([no_categorie])
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE] CHECK CONSTRAINT [articles_vendus_categories_fk]
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE]  WITH CHECK ADD  CONSTRAINT [encheres_adresse_fk] FOREIGN KEY([no_adresse_retrait])
REFERENCES [dbo].[ADRESSES] ([no_adresse])
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE] CHECK CONSTRAINT [encheres_adresse_fk]
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE]  WITH CHECK ADD  CONSTRAINT [ventes_utilisateur_fk] FOREIGN KEY([id_utilisateur])
REFERENCES [dbo].[UTILISATEURS] ([pseudo])
GO
ALTER TABLE [dbo].[ARTICLES_A_VENDRE] CHECK CONSTRAINT [ventes_utilisateur_fk]
GO
ALTER TABLE [dbo].[ENCHERES]  WITH CHECK ADD  CONSTRAINT [encheres_articles_vendus_fk] FOREIGN KEY([no_article])
REFERENCES [dbo].[ARTICLES_A_VENDRE] ([no_article])
GO
ALTER TABLE [dbo].[ENCHERES] CHECK CONSTRAINT [encheres_articles_vendus_fk]
GO
ALTER TABLE [dbo].[ENCHERES]  WITH CHECK ADD  CONSTRAINT [encheres_utilisateurs_encherisseur_fk] FOREIGN KEY([id_utilisateur])
REFERENCES [dbo].[UTILISATEURS] ([pseudo])
GO
ALTER TABLE [dbo].[ENCHERES] CHECK CONSTRAINT [encheres_utilisateurs_encherisseur_fk]
GO
ALTER TABLE [dbo].[UTILISATEURS]  WITH CHECK ADD  CONSTRAINT [utilisateur_adresse_fk] FOREIGN KEY([no_adresse])
REFERENCES [dbo].[ADRESSES] ([no_adresse])
GO
ALTER TABLE [dbo].[UTILISATEURS] CHECK CONSTRAINT [utilisateur_adresse_fk]
GO
/****** Object:  StoredProcedure [dbo].[sp_get_encheres]    Script Date: 11/04/2025 11:25:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_get_encheres]
	@all bit = 0,
	@no_article INT = NULL,
    @nom_article NVARCHAR(255) = '',
    @nom_libelle NVARCHAR(255) = '',
    @statut_enchere INT = 4,
    @is_achat bit = 1,
    @pseudo NVARCHAR(50) = ''
AS
BEGIN
    SELECT
        A.date_debut_encheres,
        A.date_fin_encheres,
        A.description,
        A.nom_article,
        A.prix_initial,
        A.prix_vente,
        A.statut_enchere,
		A.no_article,
        U.nom,
        U.prenom,
        U.pseudo,
		(
			SELECT TOP 1 E2.id_utilisateur
			FROM ENCHERES E2
			WHERE E2.no_article = A.no_article
			ORDER BY E2.montant_enchere DESC
		) AS acheteur,
        U.email,
        U.credit,
        U.telephone,
        U.administrateur,
        AD.no_adresse,
        AD.rue,
        AD.code_postal,
        AD.ville,
        C.no_categorie as id_categorie,
        C.libelle as categorie,
        AD2.no_adresse as retrait_id,
        AD2.code_postal as retrait_code_postal,
        AD2.rue as retrait_rue,
        AD2.ville as retrait_ville,
        COALESCE(MAX(E.montant_enchere), 0) AS montant_enchere,
        MAX(E.date_enchere) AS date_enchere
    FROM ARTICLES_A_VENDRE A
    LEFT JOIN ENCHERES E ON A.no_article = E.no_article
    INNER JOIN UTILISATEURS U ON A.id_utilisateur = U.pseudo
    INNER JOIN ADRESSES AD ON U.no_adresse = AD.no_adresse
    INNER JOIN CATEGORIES C ON A.no_categorie = C.no_categorie
    INNER JOIN ADRESSES AD2 ON A.no_adresse_retrait = AD2.no_adresse
        WHERE (A.nom_article LIKE '%' + @nom_article + '%')
        AND (C.libelle LIKE '%' + @nom_libelle + '%')
        AND (
			@all = 1
			OR A.statut_enchere = CASE
				WHEN @statut_enchere = 4 THEN 1
				ELSE @statut_enchere
			END
		)
        AND (
			(@statut_enchere = 4 OR @all = 1)
			OR (
				(@is_achat = 1 AND (E.id_utilisateur = @pseudo))
				OR
				(@is_achat = 0 AND A.id_utilisateur = @pseudo)
			)
		)
		AND (
				@no_article IS NULL
				OR (
						A.no_article = @no_article
					)
			)
    GROUP BY
        A.date_debut_encheres,
        A.date_fin_encheres,
        A.description,
        A.nom_article,
        A.prix_initial,
        A.prix_vente,
        A.statut_enchere,
		A.no_article,
        U.nom,
        U.prenom,
        U.pseudo,
        U.email,
        U.credit,
        U.telephone,
        U.administrateur,
        AD.no_adresse,
        AD.rue,
        AD.code_postal,
        AD.ville,
        C.no_categorie,
        C.libelle,
        AD2.no_adresse,
        AD2.code_postal,
        AD2.rue,
        AD2.ville
		ORDER BY no_article
END;
GO
