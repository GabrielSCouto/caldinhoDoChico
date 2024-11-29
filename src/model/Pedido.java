package model;

import dataBase.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Pedido {

    static Scanner scanner = new Scanner(System.in);

    public static void gerenciarPedido(Scanner scanner) throws SQLException {
        try {
            // Solicita o número do pedido ao usuário
            System.out.print("Digite o número do pedido que deseja gerenciar: ");
            ListData.listarDataPedidos();
            int idPedido = scanner.nextInt();
            scanner.nextLine();

            // Verifica se o pedido existe
            if (!ListData.verificarPedido(idPedido)) {
                System.out.println("Pedido não encontrado.");
                return;
            }

            // Exibe o menu de gerenciamento do pedido
            System.out.println("\n--- Gerenciar Pedido ---");
            System.out.println("1. Atualizar Itens do Pedido");
            System.out.println("2. Apagar Pedido");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.next();
            scanner.nextLine();

            switch (opcao) {
                case "1":
                    ListData.listarDataPedidos();
                    try {
                        // Solicita IDs dos itens a serem substituídos
                        System.out.print("Digite o ID do item que deseja substituir: ");
                        int idItemAntigo = scanner.nextInt();
                        System.out.print("Digite o ID do novo item: ");
                        int idItemNovo = scanner.nextInt();
                        updateData.atualizarItensDoPedido(idPedido, idItemAntigo, idItemNovo);
                        scanner.nextLine(); // Limpa buffer após `nextInt`
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
                        scanner.nextLine(); // Limpa buffer após erro
                    }
                    break;

                case "2":
                    ListData.listarDataPedidos();
                    RemoveData.removeDataPedido(idPedido);
                    scanner.nextLine(); // Limpa buffer
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de digitar os valores corretamente.");
            scanner.nextLine(); // Limpa buffer após erro
        }
    }

    public static void fazerPedido() throws SQLException {
        try {
            // Solicita o número da mesa ao usuário
            System.out.print("Digite o número da mesa: ");
            int numeroMesa = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer após `nextInt`

            // Verifica se a mesa está ocupada
            if (ListData.verificarMesaOcupada(numeroMesa)) {
                System.out.println("Mesa encontrada e está ocupada.");

                // Exibe os itens do cardápio
                System.out.println("Itens no cardápio:");
                ListData.listarDataCardapio(); // Método que exibe os itens do cardápio

                try {
                    // Solicita o ID do item ao usuário
                    System.out.print("Digite o ID do item para adicionar ao pedido: ");
                    int idItem = scanner.nextInt();

                    // Verifica se o item existe no cardápio
                    if (ListData.verificarItemCardapio(idItem)) {
                        InsertData.insertDataPedido(0, idItem, numeroMesa); // Insere o pedido no banco
                        enviarCozinha();
                    } else {
                        System.out.println("Item não encontrado no cardápio.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
                    scanner.nextLine(); // Limpa buffer após erro
                }
            } else {
                System.out.println("Mesa não está ocupada ou não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
            scanner.nextLine(); // Limpa buffer após erro
        }
    }

    public static void enviarCozinha() {
        // Envia o pedido para a cozinha
        System.out.println("Pedido enviado para a cozinha.");
    }
}
