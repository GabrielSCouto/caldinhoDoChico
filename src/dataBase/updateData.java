package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static dataBase.DataBaseConnection.connect;

public class updateData {


//    public static void ocuparMesa(int numero) {
//        String query = "update mesa set livre = 'ocupado' where numero = ?";
//        situacaoMesa(numero, query);
//    }

    public static void ocuparMesa() {
        int numeroMesa = solicitarNumeroMesa(); // Obtém o número da mesa validado
        String query = "UPDATE mesa SET livre = 'Ocupado' WHERE numero = ?";
        situacaoMesa(numeroMesa, query);
    }

    private static int solicitarNumeroMesa() {
        Scanner scanner = new Scanner(System.in);
        int numeroMesa = -1; // Valor inicial para controle
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Digite o número da mesa que deseja ocupar: ");
                numeroMesa = scanner.nextInt();
                entradaValida = true; // Entrada válida, sai do loop
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        return numeroMesa;
    }

    public static void desocuparMesa(int numero) {
        String query = "update mesa set livre = 'livre' where numero = ?";
        situacaoMesa(numero, query);
    }

//    public static void situacaoMesa(int numero, String query) {
//        try (Connection connection = connect()) {
//            assert connection != null;
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//
//                statement.setInt(1, numero);
//
//                int rowsUpdated = statement.executeUpdate();
//                if (rowsUpdated > 0) {
//                    System.out.println("Status da mesa atualizado com sucesso!");
//                } else {
//                    System.out.println("Mesa não encontrada para atualizar.");
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao alterar dados: " + e.getMessage());
//        }
//    }

    private static void situacaoMesa(int numero, String query) {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, numero);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Mesa " + numero + " foi marcada como ocupada com sucesso!");
            } else {
                System.out.println("Mesa " + numero + " não encontrada. Verifique o número e tente novamente.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a mesa: " + e.getMessage());
        }
    }

    public static void atualizarItensDoPedido(int idPedido, int idItemAntigo, int idItemNovo) {
        String query = "UPDATE pedidos SET idItem = ? WHERE idPedido = ? AND idItem = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idItemNovo);
                statement.setInt(2, idPedido);
                statement.setInt(3, idItemAntigo);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Itens do pedido atualizados com sucesso!");
                } else {
                    System.out.println("Item não encontrado no pedido ou atualização falhou.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar itens do pedido: " + e.getMessage());
        }
    }

    public static void atualizarItemCardapio(int idItem, String nome, String descricao, double preco) {
        String query = "UPDATE itemcardapio SET nome = ?, descricao = ?, preco = ? WHERE idItem = ?";

        try (Connection connection = DataBaseConnection.connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nome); // Nome atualizado
                statement.setString(2, descricao); // Descrição atualizada
                statement.setDouble(3, preco); // Preço atualizado
                statement.setInt(4, idItem); // ID do item a ser atualizado

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum item encontrado com o ID fornecido.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o item do cardápio: " + e.getMessage());
        }
    }
}
