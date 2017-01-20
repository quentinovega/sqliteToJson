import fr.alliancesoftware.functional.Option;
import fr.alliancesoftware.json.Syntax;
import fr.alliancesoftware.sql.SQL;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.util.sqlite.SQLiteDataType;
import org.reactivecouchbase.json.JsArray;
import org.reactivecouchbase.json.JsObject;
import org.reactivecouchbase.json.Json;

import java.sql.Connection;

import static fr.alliancesoftware.sql.SQLTools.sql;
import static org.jooq.impl.DSL.one;
import static org.jooq.util.maven.example.Tables.*;
import static org.jooq.util.maven.example.tables.Livre.LIVRE;
import static org.jooq.util.sqlite.SQLiteDSL.rowid;
import static org.reactivecouchbase.json.Syntax.$;

public class databaseSelectionUtil {

    public static JsObject getBook(Connection connection) {
        SQL sql = sql(connection, "select rowid as id, titre, sous_titre, prix,  from livre");
        fr.alliancesoftware.json.JsArray titles = fr.alliancesoftware.json.Json.arr(sql.executeQuery((row, i) -> Option.some(
                fr.alliancesoftware.json.Json.obj(
                        Syntax.$("titre", row.str("titre"))
                )
        )));

        return Json.obj();
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