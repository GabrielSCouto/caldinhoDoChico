package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dataBase.DataBaseConnection.connect;

public class InsertData {


    public static void insertDataCardapio(int idItem, String nome, String descricao, double preco) {
        String query = "insert into itemcardapio values (?,?,?,?) ";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idItem);
                statement.setString(2, nome);
                statement.setString(3, descricao);
                statement.setDouble(4, preco);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }


    public static void insertDataMesa(int numero, String livre) {
        String query = "insert into mesa values (?,?) ";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, numero);
                statement.setString(2, livre);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }




}
