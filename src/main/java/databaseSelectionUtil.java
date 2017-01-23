import fr.alliancesoftware.functional.Option;
import fr.alliancesoftware.json.JsArray;
import fr.alliancesoftware.json.JsObject;
import fr.alliancesoftware.json.Json;
import fr.alliancesoftware.sql.SQL;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;

import static fr.alliancesoftware.json.Syntax.$;
import static fr.alliancesoftware.sql.SQLTools.sql;

public class databaseSelectionUtil {

    public static JsObject getCollection(Connection connection) throws IOException {
        File requestFile = new File(databaseSelectionUtil.class.getClassLoader().getResource("book.sql").getFile());
        String request = FileUtils.readFileToString(requestFile, Charset.defaultCharset());
        SQL sql = sql(connection, request);
        //fixme: replace row.str() par le bon type d'attribut

        JsObject collection = sql.executeQuery((row, i) -> Option.some(
                Json.obj(
                        $(row.str("id_livre"), Json.obj(
                                $("title", row.str("titre")),
                                $("subTitle", row.str("sous_titre")),
                                $("price", row.bigDec("prix")),
                                $("isbn", row.str("isbn")),
                                $("publicationDate", row.str("date_publication")),
                                $("purchaseDate", row.str("date_achat")),
                                $("comment", row.str("commentaire")),
                                $("synopsis", row.str("resume")),
                                $("award", row.str("recompense")),
                                $("serie", Json.obj(
                                        $("id", row.intgr("id_serie")),
                                        $("name", row.str("nom_serie"))
                                )),
                                $("author", Json.obj(
                                        $("id", row.intgr("id_auteur")),
                                        $("name", row.str("nom_auteur"))
                                )),
                                $("artist", Json.obj(
                                        $("id", row.intgr("id_artiste")),
                                        $("name", row.str("nom_artiste"))
                                )),
                                $("format", Json.obj(
                                        $("id", row.intgr("id_format")),
                                        $("label", row.str("nom_format"))
                                )),
                                $("editor", Json.obj(
                                        $("id", row.intgr("id_editeur")),
                                        $("name", row.str("nom_editeur"))
                                )),
                                $("condition", Json.obj(
                                        $("id", row.intgr("id_etat")),
                                        $("label", row.str("nom_etat"))
                                )),
                                $("style", Json.obj(
                                        $("id", row.intgr("id_genre")),
                                        $("name", row.str("nom_genre"))
                                ))
                        ))
                )
        )).stream().reduce(Json.obj(), JsObject::add);

        return Json.obj($("comics", collection));
    }

    //todo: permettre de remmetre les nom des artiste et auteur dans le bon ordre
    private String toHumanReadbleName(String name) {

        return null;
    }

    public static JsArray getSeries(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM serie")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getArtists(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM artiste")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getAuthors(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM auteur")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getEditors(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM editeur")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getFormats(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM format")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getStyles(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM genre")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getLocations(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM localisation")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }

    public static JsArray getConditions(Connection connection) {
        return Json.arr(sql(connection, "SELECT _ROWID_ as id, nom as name FROM etat")
                .executeQuery((row, i) -> Option.some(
                        Json.obj(
                                $("id", row.intgr("id")),
                                $("name", row.str("name"))
                        )
                )));
    }


}