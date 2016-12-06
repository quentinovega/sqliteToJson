import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.util.sqlite.SQLiteDataType;
import org.reactivecouchbase.json.JsArray;
import org.reactivecouchbase.json.Json;

import static org.jooq.impl.DSL.one;
import static org.jooq.util.maven.example.Tables.*;
import static org.jooq.util.maven.example.tables.Livre.LIVRE;
import static org.jooq.util.sqlite.SQLiteDSL.rowid;
import static org.reactivecouchbase.json.Syntax.$;

public class databaseSelectionUtil {

    public static JsArray getBook(DSLContext create) {
        Result<?> result =
                create
                        .select(
                                rowid().as("id"),
                                LIVRE.TITRE,
                                LIVRE.SOUS_TITRE,
                                LIVRE.PRIX,
                                LIVRE.ID_AUTEUR.cast(SQLiteDataType.BIGINT).as("id_auteur"),
                                LIVRE.ID_ARTISTE.cast(SQLiteDataType.BIGINT).as("id_artiste"),
                                LIVRE.ID_FORMAT,
                                LIVRE.ID_EDITEUR,
                                LIVRE.ID_GENRE,
                                LIVRE.ID_LOCALISATION,
                                LIVRE.ISBN,
                                LIVRE.DATE_PUBLICATION.cast(SQLiteDataType.DATE).as("publication"),
                                LIVRE.COMMENTAIRE,
                                LIVRE.RESUME,
                                LIVRE.DATE_ACHAT.cast(SQLiteDataType.DATE).as("achat"),
                                LIVRE.RECOMPENSE
                        )
                        .from(LIVRE)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("title", record.getValue(LIVRE.TITRE)),
                        $("subTitle", record.getValue(LIVRE.SOUS_TITRE)),
                        $("price", record.getValue(LIVRE.PRIX).longValue()),
                        $("author", (Long) record.getValue("id_auteur")),
                        $("artist", (Long) record.getValue("id_artiste")),
                        $("format", record.getValue(LIVRE.ID_FORMAT)),
                        $("editor", record.getValue(LIVRE.ID_EDITEUR)),
                        $("style", record.getValue(LIVRE.ID_GENRE)),
                        $("location", record.getValue(LIVRE.ID_LOCALISATION)),
                        $("isbn", (String) record.getValue(LIVRE.ISBN)),
                        $("publicationDate", record.getValue("publication").toString()),
                        $("comment", record.getValue(LIVRE.COMMENTAIRE)),
                        $("synopsis", record.getValue(LIVRE.RESUME)),
                        $("buyingDate", record.getValue("achat").toString()),
                        $("award", record.getValue(LIVRE.RECOMPENSE))
                )));


    }

    public static JsArray getSeries(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), SERIE.NOM)
                        .from(SERIE)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(SERIE.NOM))
                )));
    }

    public static JsArray getArtists(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), ARTISTE.NOM)
                        .from(ARTISTE)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(ARTISTE.NOM))
                )));
    }

    public static JsArray getAuthors(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), AUTEUR.NOM)
                        .from(AUTEUR)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(AUTEUR.NOM))
                )));
    }

    public static JsArray getEditors(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), EDITEUR.NOM)
                        .from(EDITEUR)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(EDITEUR.NOM))
                )));
    }

    public static JsArray getFormats(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), FORMAT.NOM)
                        .from(FORMAT)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(FORMAT.NOM))
                )));
    }

    public static JsArray getStyles(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), GENRE.NOM)
                        .from(GENRE)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(GENRE.NOM))
                )));
    }

    public static JsArray getLocations(DSLContext create) {
        Result<?> result =
                create
                        .select(rowid().as("id"), LOCALISATION.NOM)
                        .from(LOCALISATION)
                        .orderBy(one().asc())
                        .fetch();

        return Json.arr(
                result.map(record -> Json.obj(
                        $("id", (Long) record.getValue("id")),
                        $("name", record.getValue(LOCALISATION.NOM))
                )));
    }


}