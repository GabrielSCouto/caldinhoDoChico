package model;

import dataBase.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Pedido {

    static Scanner scanner = new Scanner(System.in);

    public static void gerenciarPedido(Scanner scanner) throws SQLException, InputMismatchException {
        System.out.print("Digite o número do pedido que deseja gerenciar: ");
        ListData.listarDataPedidos();
        int idPedido = scanner.nextInt();

        // Verifica se o pedido existe
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
                System.out.print("Digite o ID do item que deseja substituir: ");
                int idItemAntigo = scanner.nextInt();
                System.out.print("Digite o ID do novo item: ");
                int idItemNovo = scanner.nextInt();
                updateData.atualizarItensDoPedido(idPedido, idItemAntigo, idItemNovo);
                scanner.nextLine();
                break;

            case "2":
                ListData.listarDataPedidos();
                RemoveData.removeDataPedido(idPedido);
                scanner.nextLine();
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }


    public static void fazerPedido() throws SQLException, InputMismatchException {
        System.out.print("Digite o número da mesa: ");
        int numeroMesa = scanner.nextInt();


        // Verifica se a mesa está ocupada
        if (ListData.verificarMesaOcupada(numeroMesa)) {
            System.out.println("Mesa encontrada e está ocupada.");

            System.out.println("Itens no cardápio:");
            ListData.listarDataCardapio(); // Método que exibe os itens do cardápio

            System.out.print("Digite o ID do item para adicionar ao pedido: ");
            int idItem = scanner.nextInt();

            // Verifica se o item existe no cardápio
            if (ListData.verificarItemCardapio(idItem)) {
                InsertData.insertDataPedido(0,idItem,numeroMesa); // Insere o pedido no banco
                enviarCozinha();
            } else {
                System.out.println("Item não encontrado no cardápio.");
            }
        } else {
            System.out.println("Mesa não está ocupada ou não encontrada.");
        }
    }

    public static void enviarCozinha() {
            System.out.println("Pedido enviado para a cozinha.");
    }


}