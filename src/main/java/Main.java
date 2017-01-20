import fr.alliancesoftware.functional.Option;
import fr.alliancesoftware.json.JsArray;
import fr.alliancesoftware.json.Json;
import fr.alliancesoftware.sql.SQL;

import java.sql.Connection;
import java.sql.SQLException;

import static fr.alliancesoftware.json.Syntax.$;
import static fr.alliancesoftware.sql.SQLTools.sql;


public class Main {

    public static void main(String[] args) {
        Connexion connexion = new Connexion("comic.db");

        try (Connection conn = connexion.connect()) {
            SQL sql = sql(conn, "select titre from livre");
            JsArray titles = Json.arr(sql.executeQuery((row, i) -> Option.some(
                    Json.obj(
                            $("titre", row.str("titre"))
                    )
            )));

            System.out.println(titles.stringify(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connexion.close();
    }
}
