package view;

import dataBase.*;

import dataBase.updateData;
import model.*;

import java.sql.SQLException;
import java.util.Scanner;

//importado pra adicao manual de mesas mesas.add funcionar
//esses to tentando saber pq da erro ainda (DESCOBRI, TEM Q DEIXAR PUBLIC)
import static model.ItemCardapio.cardapio;
import static model.Pedido.pedidos;

public class Menu {
    public static void menu() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Criando algumas mesas iniciais
        mesas.add(new Mesa(1));
        mesas.add(new Mesa(2));
        mesas.add(new Mesa(3));
        mesas.add(new Mesa(4));
        mesas.add(new Mesa(5));
        mesas.add(new Mesa(6));
        mesas.add(new Mesa(7));
        mesas.add(new Mesa(8));
        mesas.add(new Mesa(9));
        mesas.add(new Mesa(10));

        InsertData.insertDataMesa(1,"livre");
        InsertData.insertDataMesa(2,"livre");
        InsertData.insertDataMesa(3,"livre");
        InsertData.insertDataMesa(4,"livre");
        InsertData.insertDataMesa(5,"livre");
        InsertData.insertDataMesa(6,"livre");
        InsertData.insertDataMesa(7,"livre");
        InsertData.insertDataMesa(8,"livre");
        InsertData.insertDataMesa(9,"livre");
        InsertData.insertDataMesa(10,"livre");

        Colaborador colaborador = new Colaborador();
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
                    System.out.print("Digite o número da mesa para ocupar: \n");
                    int numMesa1 = scanner.nextInt();
                    updateData.ocuparMesa(numMesa1);
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
                    System.out.println("3. Listar Cardápio\n");
                    System.out.print("Escolha uma opção: ");
                    ItemCardapio.gerenciarCardapio();
                    break;

                case "6":
//                    colaborador.gerarRelatorioPedidos(pedidos);
                    break;

                case "7":
                    Pedido.gerenciarPedido(scanner);
                    break;
                case "8":
                    colaborador.sair();
                    break;

                default:
                    break;
            }
        } while (!opcao.equals("8"));
    }
}