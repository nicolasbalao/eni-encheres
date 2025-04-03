CREATE PROCEDURE sp_get_encheres
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
        C.no_categorie as id_categorie,
        C.libelle as categorie,
        AD2.no_adresse as retrait_id,
        AD2.code_postal as retrait_code_postal,
        AD2.rue as retrait_rue,
        AD2.ville as retrait_ville,
        MAX(E.montant_enchere) as montant_enchere,
        MAX(E.date_enchere) AS date_enchere
    FROM ARTICLES_A_VENDRE A
    LEFT JOIN ENCHERES E ON A.no_article = E.no_article
    INNER JOIN UTILISATEURS U ON A.id_utilisateur = U.pseudo
    INNER JOIN ADRESSES AD ON U.no_adresse = AD.no_adresse
    INNER JOIN CATEGORIES C ON A.no_categorie = C.no_categorie
    INNER JOIN ADRESSES AD2 ON A.no_adresse_retrait = AD2.no_adresse
        WHERE (A.nom_article LIKE '%' + @nom_article + '%')
        AND (C.libelle LIKE '%' + @nom_libelle + '%')
        AND A.statut_enchere = CASE 
			WHEN @statut_enchere = 4 THEN 1 
			ELSE @statut_enchere 
		END
        AND (
			@statut_enchere = 4
			OR (
				(@is_achat = 1 AND (E.id_utilisateur = @pseudo))
				OR
				(@is_achat = 0 AND A.id_utilisateur = @pseudo)
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
        AD2.ville;
END;
