package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dataBase.DataBaseConnection.connect;

public class updateData {


    public static void ocuparMesa(int numero) {
        String query = "update mesa set livre = 'ocupado' where numero = ?";
        situacaoMesa(numero, query);
    }

    public static void desocuparMesa(int numero) {
        String query = "update mesa set livre = 'livre' where numero = ?";
        situacaoMesa(numero, query);
    }

    public static void situacaoMesa(int numero, String query) {
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, numero);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Status da mesa atualizado com sucesso!");
                } else {
                    System.out.println("Mesa não encontrada para atualizar.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar dados: " + e.getMessage());
        }
    }
}
