package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsertExample {

    private static final String URL = "jdbc:mysql://localhost:3306/caldinho_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão bem-sucedida!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static void insertData(int numero, String livre) {
        String query = "INSERT INTO mesa (numero,livre) VALUES (?, ?)";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                // Configura os valores da consulta
                statement.setInt(1, numero);
                statement.setString(2, livre);

                // Executa a inserção
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Inserindo dados
        insertData(2, "livre");
    }
}
