USE [master]
GO
/****** Object:  Database [ENCHERES_DB]    Script Date: 11/04/2025 10:46:06 ******/
CREATE DATABASE [ENCHERES_DB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ENCHERES_DB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\ENCHERES_DB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ENCHERES_DB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\ENCHERES_DB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [ENCHERES_DB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ENCHERES_DB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ENCHERES_DB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET ARITHABORT OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ENCHERES_DB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ENCHERES_DB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ENCHERES_DB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ENCHERES_DB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ENCHERES_DB] SET  MULTI_USER 
GO
ALTER DATABASE [ENCHERES_DB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ENCHERES_DB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ENCHERES_DB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ENCHERES_DB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ENCHERES_DB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ENCHERES_DB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ENCHERES_DB] SET QUERY_STORE = ON
GO
ALTER DATABASE [ENCHERES_DB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [ENCHERES_DB]
GO
/****** Object:  Table [dbo].[ADRESSES]    Script Date: 11/04/2025 10:46:06 ******/
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
/****** Object:  Table [dbo].[ARTICLES_A_VENDRE]    Script Date: 11/04/2025 10:46:06 ******/
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
/****** Object:  Table [dbo].[CATEGORIES]    Script Date: 11/04/2025 10:46:06 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [uc_categories] UNIQUE NONCLUSTERED 
(
	[libelle] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ENCHERES]    Script Date: 11/04/2025 10:46:06 ******/
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
/****** Object:  Table [dbo].[ROLES]    Script Date: 11/04/2025 10:46:06 ******/
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
/****** Object:  Table [dbo].[UTILISATEURS]    Script Date: 11/04/2025 10:46:06 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [uc_utilisateurs] UNIQUE NONCLUSTERED 
(
	[pseudo] ASC,
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
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
/****** Object:  StoredProcedure [dbo].[sp_get_encheres]    Script Date: 11/04/2025 10:46:06 ******/
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
USE [master]
GO
ALTER DATABASE [ENCHERES_DB] SET  READ_WRITE 
GO
