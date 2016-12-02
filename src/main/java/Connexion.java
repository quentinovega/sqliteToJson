import java.sql.*;

public class Connexion {
    private String DBPath = "Chemin aux base de donnée SQLite";
    private Connection connection = null;
    private Statement statement = null;

    public Connexion(String dBPath) {
        DBPath = dBPath;
    }

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException | SQLException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion");
        }

        return connection;
    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : " + sql);
        }
        return resultat;

    }
}