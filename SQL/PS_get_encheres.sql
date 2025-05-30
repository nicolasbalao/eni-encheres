USE [ENCHERES_DB]
GO
/****** Object:  StoredProcedure [dbo].[sp_get_encheres]    Script Date: 04/04/2025 17:37:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_get_encheres]
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
