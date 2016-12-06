import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        Connexion connexion = new Connexion("comic.db");

        try (Connection conn = connexion.connect()) {
            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
            databaseSelectionUtil.getBook(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connexion.close();


    }
}
