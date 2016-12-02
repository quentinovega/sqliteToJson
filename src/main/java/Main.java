import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.util.maven.example.tables.Serie;

import java.sql.Connection;
import java.sql.SQLException;
import static org.jooq.util.maven.example.tables.Livre.LIVRE;

public class Main {

    public static void main(String[] args) {
        Connexion connexion = new Connexion("comic.db");

        try (Connection conn = connexion.connect()) {
            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
            Result<?> result =
                    create
                            .select(LIVRE.TITRE, LIVRE.SOUS_TITRE, LIVRE.ID_SERIE)
                            .from(LIVRE)
                            .where(LIVRE.ID_SERIE.equal(2))
                            .fetch();

            result.forEach(record -> {
                String title = record.getValue(LIVRE.TITRE);
                String subtitle = record.getValue(LIVRE.SOUS_TITRE);
                int idSerie = record.getValue(LIVRE.ID_SERIE);
                System.out.println(title +" - "+subtitle +" - "+idSerie);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connexion.close();


    }
}
