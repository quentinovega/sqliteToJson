import fr.alliancesoftware.json.JsObject;
import fr.alliancesoftware.json.Json;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static fr.alliancesoftware.json.Syntax.$;


public class Main {

    public static void main(String[] args) {
        Connexion connexion = new Connexion("comic.db");

        try (Connection conn = connexion.connect()) {
            JsObject database = Json.obj(
                    $("comics", databaseSelectionUtil.getCollection(conn)),
                    $("authors", databaseSelectionUtil.getAuthors(conn)),
                    $("artists", databaseSelectionUtil.getArtists(conn)),
                    $("editors", databaseSelectionUtil.getEditors(conn)),
                    $("formats", databaseSelectionUtil.getFormats(conn)),
                    $("locations", databaseSelectionUtil.getLocations(conn)),
                    $("styles", databaseSelectionUtil.getStyles(conn)),
                    $("condition", databaseSelectionUtil.getConditions(conn))
            );

            //todo: export in file
            System.out.println(database.stringify(true));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        connexion.close();
    }
}
