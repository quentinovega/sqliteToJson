WITH auteurs AS (
    SELECT _ROWID_ as id_auteur, nom as nom_auteur
    FROM auteur
),

    artistes AS (
    SELECT _ROWID_ as id_artiste, nom as nom_artiste
    FROM artiste
),

  editeurs AS (
      SELECT _ROWID_ as id_editeur, nom as nom_editeur
      FROM editeur
  ),

  etats AS (
      SELECT _ROWID_ as id_etat, nom as nom_etat
      FROM etat
  ),

  formats AS (
    SELECT _ROWID_ as id_format, nom as nom_format
    from format
  ),

  genres AS (
    SELECT _ROWID_ as id_genre, nom as nom_genre
    from genre
  ),

  localisations AS (
    SELECT _ROWID_ as id_localisation, nom as nom_localisation
    from localisation
  ),

  series AS (
    SELECT _ROWID_ as id_serie, nom as nom_serie
    from serie
  ),

  livres AS (
      SELECT _ROWID_ as id_livre, titre, sous_titre, id_serie, prix, id_auteur, id_artiste, id_format, id_editeur, id_genre, id_etat, id_localisation, isbn, date_publication, commentaire,
        resume, date_achat, recompense FROM livre
  )

SELECT id_livre, titre, sous_titre, prix
  , l.id_serie, nom_serie
  , l.id_auteur, nom_auteur
  , l.id_artiste, nom_artiste
  , l.id_format, nom_format
  , l.id_editeur, nom_editeur
  , l.id_genre, nom_genre
  , l.id_etat, nom_etat
  , l.id_localisation, nom_localisation
  , l.isbn, date_publication, commentaire,
  resume, date_achat, recompense
FROM livres l
INNER JOIN auteurs ON livres.id_auteur = auteurs.id_auteur
INNER JOIN artistes ON livres.id_artiste = artistes.id_artiste
INNER JOIN editeurs on livres.id_editeur = editeurs.id_editeur
INNER JOIN etats on livres.id_etat = etats.id_etat
INNER JOIN formats on livres.id_format = formats.id_format
INNER JOIN genres on livres.id_genre = genres.id_genre
INNER JOIN localisations on livres.id_localisation = localisations.id_localisation
INNER JOIN series on livres.id_serie = series.id_serie;
