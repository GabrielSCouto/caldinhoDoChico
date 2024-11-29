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

    public static void listarPedidosComDetalhes() {
        String query = """
            SELECT\s
                p.idPedido AS IdPedido,
                p.idItem AS IdItem,
                i.nome AS Nome,
                i.descricao AS Descricao,
                i.preco AS Preco,
                m.numero AS NumeroMesa
            FROM\s
                pedidos p
            JOIN\s
                itemcardapio i ON p.idItem = i.idItem
            JOIN\s
                mesa m ON p.numeroMesa = m.numero
            ORDER BY\s
                p.idPedido;
           \s""";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Pedidos Detalhados:");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-10s %-20s %-20s %-30s %-5s\n",
                        "IdPedido", "IdItem","NumeroMesa", "Nome", "Preco", "Descricao");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                while (resultSet.next()) {
                    int idPedido = resultSet.getInt("IdPedido");
                    int idItem = resultSet.getInt("IdItem");
                    int numeroMesa = resultSet.getInt("NumeroMesa");
                    String nome = resultSet.getString("Nome");
                    String descricao = resultSet.getString("Descricao");
                    double preco = resultSet.getDouble("Preco");


                    System.out.printf("%-10d %-10d %-10d %-30s R$%-10.2f %-30s \n",
                            idPedido, idItem, numeroMesa, nome, preco, descricao);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os pedidos: " + e.getMessage());
        }
    }

    //atualizar item no cardapio
//    public static void atualizarItemCardapio(int idItem, String nome, String descricao, double preco) {
//        String query = "UPDATE itemcardapio SET nome = ?, descricao = ?, preco = ? WHERE idItem = ?";
//
//        try (Connection connection = DataBaseConnection.connect()) {
//            assert connection != null;
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                statement.setString(1, nome); // Nome atualizado
//                statement.setString(2, descricao); // Descrição atualizada
//                statement.setDouble(3, preco); // Preço atualizado
//                statement.setInt(4, idItem); // ID do item a ser atualizado
//
//                int rowsAffected = statement.executeUpdate();
//                if (rowsAffected > 0) {
//                    System.out.println("Item atualizado com sucesso!");
//                } else {
//                    System.out.println("Nenhum item encontrado com o ID fornecido.");
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao atualizar o item do cardápio: " + e.getMessage());
//        }
//    }



}
