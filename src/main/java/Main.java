import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.reactivecouchbase.json.JsObject;
import org.reactivecouchbase.json.Json;

import java.sql.Connection;
import java.sql.SQLException;

import static org.reactivecouchbase.json.Syntax.$;


public class Main {

    public static void main(String[] args) {
        Connexion connexion = new Connexion("comic.db");

        try (Connection conn = connexion.connect()) {
            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
            databaseSelectionUtil.getBook(create);

            JsObject database = Json.obj(
                    $("books", databaseSelectionUtil.getBook(create)),
                    $("series", databaseSelectionUtil.getSeries(create)),
                    $("artists", databaseSelectionUtil.getArtists(create)),
                    $("authors", databaseSelectionUtil.getAuthors(create)),
                    $("editors", databaseSelectionUtil.getEditors(create)),
                    $("formats", databaseSelectionUtil.getFormats(create)),
                    $("styles", databaseSelectionUtil.getStyles(create)),
                    $("locations", databaseSelectionUtil.getLocations(create))
            );

            System.out.println(database.stringify(true));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        connexion.close();


    }
}
