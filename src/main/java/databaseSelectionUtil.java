import fr.alliancesoftware.functional.Option;
import fr.alliancesoftware.json.Syntax;
import fr.alliancesoftware.sql.SQL;
import org.apache.commons.io.FileUtils;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.util.sqlite.SQLiteDataType;
import org.reactivecouchbase.json.JsArray;
import org.reactivecouchbase.json.JsObject;
import org.reactivecouchbase.json.Json;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.Connection;

import static fr.alliancesoftware.sql.SQLTools.sql;
import static org.jooq.impl.DSL.one;
import static org.jooq.util.maven.example.Tables.*;
import static org.jooq.util.maven.example.tables.Livre.LIVRE;
import static org.jooq.util.sqlite.SQLiteDSL.rowid;
import static org.reactivecouchbase.json.Syntax.$;

public class databaseSelectionUtil {

    public static JsObject getCollection(Connection connection) throws IOException {
        File requestFile =  new File(databaseSelectionUtil.class.getClassLoader().getResource("book.sql").getFile());
        String request = FileUtils.readFileToString(requestFile, Charset.defaultCharset());
        SQL sql = sql(connection, request);
        //fixme: replace row.str() par le bon type d'attribut
        JsObject collection = sql.executeQuery((row, i) -> Option.some(
                Json.obj(
                        $(row.str("id_livre"), Json.obj(
                            $("title", row.str("titre")),
                            $("subTitle", row.str("sous_titre")),
                            $("price", row.str("prix")),
                            $("isbn", row.str("isbn")),
                            $("publicationDate", row.str("date_publication")),
                            $("purchaseDate", row.str("date_achat")),
                            $("comment", row.str("commentaire")),
                            $("synopsis", row.str("resume")),
                            $("award", row.str("recompense")),
                            $("serie", Json.obj(
                                    $("id", row.str("id_serie")),
                                    $("name", row.str("nom_serie"))
                            ),
                            $("author", Json.obj(
                                    $("id", row.str("id_auteur")),
                                    $("name", row.str("nom_auteur"))
                            ),
                            $("artist", Json.obj(
                                    $("id", row.str("id_artist")),
                                    $("name", row.str("nom_artist"))
                            ),
                            $("format", Json.obj(
                                    $("id", row.str("id_format")),
                                    $("label", row.str("nom_format"))
                            ),
                            $("editor", Json.obj(
                                    $("id", row.str("id_editeur")),
                                    $("name", row.str("nom_editeur"))
                            ),
                            $("condition", Json.obj(
                                    $("id", row.str("id_etat")),
                                    $("label", row.str("nom_etat"))
                            ),
                            $("style", Json.obj(
                                    $("id", row.str("id_genre")),
                                    $("name", row.str("nom_genre"))
                            )
                        ))
                )
        )));

        return Json.obj("comics", collection);
    }

    //todo: permettre de remmetre les nom des artiste et auteur dans le bon ordre
    private String toHumanReadbleName(String name) {

        return null;
    }

    public static JsArray getSeries(Connection connection) {

        return null;
    }

    public static JsArray getArtists(Connection connection) {

        return null;
    }

    public static JsArray getAuthors(Connection connection) {

        return null;
    }

    public static JsArray getEditors(Connection connection) {

        return null;
    }

    public static JsArray getFormats(Connection connection) {

        return null;
    }

    public static JsArray getStyles(Connection connection) {

        return null;
    }

    public static JsArray getLocations(Connection connection) {

        return null;
    }


}