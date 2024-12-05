package model;

import dataBase.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Pedido {

    static Scanner scanner = new Scanner(System.in);

    public static void gerenciarPedido(Scanner scanner) throws SQLException {
        try {
            System.out.print("Digite o número do pedido que deseja gerenciar: ");
            ListData.listarDataPedidos();
            int idPedido = scanner.nextInt();
            scanner.nextLine();

            if (!ListData.verificarPedido(idPedido)) {
                System.out.println("Pedido não encontrado.");
                return;
            }

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
                        System.out.print("Digite o ID do item que deseja substituir: ");
                        int idItemAntigo = scanner.nextInt();
                        System.out.print("Digite o ID do novo item: ");
                        int idItemNovo = scanner.nextInt();
                        updateData.atualizarItensDoPedido(idPedido, idItemAntigo, idItemNovo);
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
                        scanner.nextLine();
                    }
                    break;

                case "2":
                    ListData.listarDataPedidos();
                    RemoveData.removeDataPedido(idPedido);
                    scanner.nextLine();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de digitar os valores corretamente.");
            scanner.nextLine();
        }
    }

    public static void fazerPedido() throws SQLException {
        try {
            System.out.print("Digite o número da mesa: ");
            int numeroMesa = scanner.nextInt();
            scanner.nextLine();

            if (ListData.verificarMesaOcupada(numeroMesa)) {
                System.out.println("Mesa encontrada e está ocupada.");
                System.out.println("Itens no cardápio:");
                ListData.listarDataCardapio();

                try {
                    System.out.print("Digite o ID do item para adicionar ao pedido: ");
                    int idItem = scanner.nextInt();

                    if (ListData.verificarItemCardapio(idItem)) {
                        InsertData.insertDataPedido(0, idItem, numeroMesa);
                        enviarCozinha();
                    } else {
                        System.out.println("Item não encontrado no cardápio.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Mesa não está ocupada ou não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
            scanner.nextLine();
        }
    }

    public static void enviarCozinha() {
        System.out.println("Pedido enviado para a cozinha.");
    }
}
