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

    public static void listarDataPedidos() throws SQLException {
        String query = "select * from pedidos";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query);

                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Lista de pedidos: ");
                System.out.println("-----------------------------------------");

                while (resultSet.next()) {
                    int idPedido = resultSet.getInt("idPedido");
                    int numeroMesa = resultSet.getInt("numeroMesa");
                    int idItem = resultSet.getInt("idItem");

                    System.out.printf("ID do pedido: %d\nNumero da mesa: %d\nID do item: %d\n", idPedido, numeroMesa, idItem);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os pedidos: " + e.getMessage());
        }

    }

    public static boolean verificarMesaOcupada(int numeroMesa) {
        String query = "SELECT livre FROM mesa WHERE numero = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, numeroMesa);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBoolean("livre");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar o status da mesa: " + e.getMessage());
        }
        return true;
    }

    public static boolean verificarItemCardapio(int iditem) {
        String query = "SELECT COUNT(*) AS total FROM itemcardapio WHERE iditem = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, iditem);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("total") > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar o item no cardápio: " + e.getMessage());
        }
        return false;
    }

    public static void listarItensPedido(int idPedido) {
        String query = "SELECT c.idItem, c.nome, c.descricao, c.preco FROM pedidos_ip " +
                "JOIN itemcardapio c ON ip.idItem = c.id WHERE ip.idPedido = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idPedido);
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Itens no Pedido:");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nome = resultSet.getString("nome");
                        String descricao = resultSet.getString("descricao");
                        double preco = resultSet.getDouble("preco");
                        System.out.printf("ID: %d | Nome: %s | Descricao: %s| Preço: %.2f\n", id, nome, descricao, preco);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os itens do pedido: " + e.getMessage());
        }
    }

    public static boolean verificarPedido(int idPedido) {
        String query = "SELECT COUNT(*) AS total FROM pedidos WHERE idPedido = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idPedido);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("total") > 0; // Retorna true se o pedido existe
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar o pedido: " + e.getMessage());
        }
        return false;
    }







}
