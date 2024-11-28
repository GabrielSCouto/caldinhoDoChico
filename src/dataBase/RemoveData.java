package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dataBase.DataBaseConnection.connect;

public class RemoveData {

    public static void removeDataCardapio(int idItem) {
        String query = "delete from itemcardapio where idItem = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idItem);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("remoção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover dados: " + e.getMessage());
        }
    }


    public static void removeDataPedido (int idPedido) {
        String query = "delete from pedidos where idPedido = ?";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idPedido);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("remoção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover dados: " + e.getMessage());
        }
    }


}
