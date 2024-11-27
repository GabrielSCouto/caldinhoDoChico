package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dataBase.DataBaseConnection.connect;

public class ListData {


    public static void listarDataCardapio() throws SQLException {
        String query = "select * from itemcardapio";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query);

                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Itens do Cardápio:");
                System.out.println("-----------------------------------------");

                while (resultSet.next()) {
                    int idItem = resultSet.getInt("idItem");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    double preco = resultSet.getDouble("preco");

                    System.out.printf("ID: %d\nNome: %s\nDescrição: %s\nPreço: %.2f\n", idItem, nome, descricao, preco);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens do cardápio: " + e.getMessage());
        }
    }

    public static void listarDataMesa() throws SQLException {
        String query = "select * from mesa";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query);

                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Lista de mesas:");
                System.out.println("-----------------------------------------");

                // Itera pelos resultados
                while (resultSet.next()) {
                    int numero = resultSet.getInt("numero");
                    String livre = resultSet.getString("livre");

                    System.out.printf("Numero: %d\nSituação: %s\n", numero,livre);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar mesas: " + e.getMessage());
        }
    }
}
