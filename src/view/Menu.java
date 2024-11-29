package view;

import dataBase.*;

import dataBase.updateData;
import model.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    public static void menu() throws SQLException, InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        String opcao;

        //IMPRIME MENU
        do {
            System.out.println("\n--- Menu do Colaborador ---");
            System.out.println("1. Selecionar Mesa para ocupar");
            System.out.println("2. Visualizar Situação das Mesas");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Efetuar Pagamento");
            System.out.println("5. Gerenciar Cardápio");
            System.out.println("6. Gerar Relatório de Pedidos");
            System.out.println("7. Gerenciar pedidos");
            System.out.println("8. Sair\n");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    ListData.listarDataMesa();
//                    System.out.print("Digite o número da mesa para ocupar: \n");
//                    int numMesa1 = scanner.nextInt();
                    updateData.ocuparMesa();
                    scanner.nextLine();
                    break;
                case "2":
                    ListData.listarDataMesa();
                    break;

                case "3":
                    Pedido.fazerPedido();
                    break;

                case "4":
                    System.out.print("Digite o número da mesa para efetuar pagamento: ");
                    ListData.listarDataMesa();
                    int numMesa3 = scanner.nextInt();
                    updateData.desocuparMesa(numMesa3);
                    scanner.nextLine();
                    break;

                case "5":
                    System.out.println("--- Gerenciar Cardápio ---");
                    System.out.println("1. Adicionar Item");
                    System.out.println("2. Remover Item");
                    System.out.println("3. Atualizar Item");
                    System.out.println("4. Listar Cardápio\n");
                    System.out.print("Escolha uma opção: ");
                    ItemCardapio.gerenciarCardapio();
                    break;

                case "6":
                    ListData.listarPedidosComDetalhes();
                    break;

                case "7":
                    Pedido.gerenciarPedido(scanner);
                    break;
                case "8":
                    System.out.println("Saindo...");
                    break;

                default:
                    break;
            }
        } while (!opcao.equals("8"));
    }
}