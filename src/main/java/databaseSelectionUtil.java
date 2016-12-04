import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.util.maven.example.tables.Serie;
import org.jooq.util.sqlite.SQLiteDataType;

import static org.jooq.impl.DSL.one;
import static org.jooq.impl.DSL.select;
import static org.jooq.util.maven.example.Tables.*;
import static org.jooq.util.maven.example.tables.Livre.LIVRE;
import static org.jooq.util.sqlite.SQLiteDSL.rowid;

public class databaseSelectionUtil {

    public static void getBook(DSLContext create) {
        Result<?> result =
                create
                        .select(
                                rowid().as("id"),
                                LIVRE.TITRE,
                                LIVRE.SOUS_TITRE,
                                LIVRE.PRIX,
                                LIVRE.ID_AUTEUR,
                                LIVRE.ID_ARTISTE,
                                LIVRE.ID_FORMAT,
                                LIVRE.ID_EDITEUR,
                                LIVRE.ID_GENRE,
                                LIVRE.ID_LOCALISATION,
                                LIVRE.ISBN,
                                LIVRE.DATE_PUBLICATION,
                                LIVRE.COMMENTAIRE,
                                LIVRE.RESUME,
                                LIVRE.DATE_ACHAT,
                                LIVRE.RECOMPENSE
                        )
                        .from(LIVRE)
                        .innerJoin(Serie.SERIE)
                        .on(LIVRE.ID_SERIE.equal(select((rowid().cast(SQLiteDataType.INT))).from(Serie.SERIE)))
                        .fetch();

        result.forEach(record -> {
            String title = record.getValue(LIVRE.TITRE);
            String subtitle = record.getValue(LIVRE.SOUS_TITRE);
            String serie = record.getValue(Serie.SERIE.NOM);
            System.out.println(title + " - " + subtitle + " - " + serie);
        });
    }

    public static void getSerie(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), SERIE.NOM)
                        .from(SERIE)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(SERIE.NOM);
            System.out.println(id + " - " + name);
        });
    }

    public static void getArtist(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), ARTISTE.NOM)
                        .from(ARTISTE)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(ARTISTE.NOM);
            System.out.println(id + " - " + name);
        });
    }

    public static void getAuthor(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), AUTEUR.NOM)
                        .from(AUTEUR)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(AUTEUR.NOM);
            System.out.println(id + " - " + name);
        });
    }

    public static void getEditor(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), EDITEUR.NOM)
                        .from(EDITEUR)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(EDITEUR.NOM);
            System.out.println(id + " - " + name);
        });
    }

    public static void getFormat(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), FORMAT.NOM)
                        .from(FORMAT)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(FORMAT.NOM);
            System.out.println(id + " - " + name);
        });
    }

    public static void getStyle(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), GENRE.NOM)
                        .from(GENRE)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(GENRE.NOM);
            System.out.println(id + " - " + name);
        });
    }

    public static void getLocation(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), LOCALISATION.NOM)
                        .from(LOCALISATION)
                        .orderBy(one().asc())
                        .fetch();

        result.forEach(record -> {
            Long id = (Long) record.getValue("id");
            String name = record.getValue(LOCALISATION.NOM);
            System.out.println(id + " - " + name);
        });
    }


}