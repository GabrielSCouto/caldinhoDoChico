package dataBase;

import java.sql.*;

public class DataBaseConnection {

    private static final String URL = "jdbc:mariadb://localhost:3306/caldinho_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println();
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

}
