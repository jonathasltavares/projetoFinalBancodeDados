package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String dbName = "projetodb";
    private static final String dbURL = "jdbc:postgresql://localhost:5432/";
    private static final String username = "postgres";
    private static final String password = "Joncraft13.";

    public static Connection conexaoDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(dbURL.concat(dbName), username, password);
    }

    public static PreparedStatement prepararSQL(String sql) throws SQLException, ClassNotFoundException {
        Connection conexao = conexaoDB();
        return conexao.prepareStatement(sql);
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("Estado do SQL: " + ((SQLException) e).getSQLState());
                System.err.println("Código do ERRO: " + ((SQLException) e).getErrorCode());
                System.err.println("Mensagem: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Causa: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
