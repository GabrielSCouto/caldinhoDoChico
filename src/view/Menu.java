package view;

import dataBase.DatabaseInsertExample;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//importado pra adicao manual de mesas mesas.add funcionar
import static model.Pedido.mesas;
//esses to tentando saber pq da erro ainda (DESCOBRI, TEM Q DEIXAR PUBLIC)
import static model.ItemCardapio.cardapio;
import static model.Pedido.pedidos;

public class Menu {
    public static void menu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
//        List<Mesa> mesas = new ArrayList<>();
//        List<ItemCardapio> cardapio = new ArrayList<>();
//        List<Pedido> pedidos = new ArrayList<>();

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
        DatabaseInsertExample.insertDataMesa(1,"livre");
        DatabaseInsertExample.insertDataMesa(2,"livre");
        DatabaseInsertExample.insertDataMesa(3,"livre");
        DatabaseInsertExample.insertDataMesa(4,"livre");
        DatabaseInsertExample.insertDataMesa(5,"livre");
        DatabaseInsertExample.insertDataMesa(6,"livre");
        DatabaseInsertExample.insertDataMesa(7,"livre");
        DatabaseInsertExample.insertDataMesa(8,"livre");
        DatabaseInsertExample.insertDataMesa(9,"livre");
        DatabaseInsertExample.insertDataMesa(10,"livre");

        Colaborador colaborador = new Colaborador();
        String opcao;


        //IMPRIME MENU
        do {
            System.out.println("\n--- Menu do Colaborador ---");
            System.out.println("1. Selecionar Mesa para ocupar");
            System.out.println("2. Visualizar Situação das Mesas");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Enviar Pedido para Cozinha");
            System.out.println("5. Efetuar Pagamento");
            System.out.println("6. Gerenciar Cardápio");
            System.out.println("7. Gerar Relatório de Pedidos");
            System.out.println("8. Gerenciar pedidos");
            System.out.println("9. Sair\n");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    DatabaseInsertExample.listarDataMesa();
                    System.out.print("Digite o número da mesa para ocupar: \n");
                    int numMesa1 = scanner.nextInt();
                    DatabaseInsertExample.ocuparMesa(numMesa1);
                    scanner.nextLine();
                    break;
                case "2":
                    DatabaseInsertExample.listarDataMesa();
                    break;

                case "3":
                    System.out.print("Digite o número da mesa para fazer pedido: ");

                    Pedido.fazerPedido();
//                    int numMesa2 = scanner.nextInt();
//                    Mesa mesaSelecionada1 = mesas.stream().filter(m -> m.getNumero() == numMesa2).findFirst().orElse(null);
//                    if (mesaSelecionada1 != null && !mesaSelecionada1.isLivre()) {
//                        Pedido pedido = new Pedido(pedidos.size() + 1, new Date(), "Em andamento");
//                        System.out.println("Itens no cardápio:");
//                        DatabaseInsertExample.listarDataCardapio();
//                        System.out.print("Digite o ID do item para adicionar ao pedido: ");
//                        int idItem = scanner.nextInt();
//                        ItemCardapio itemPedido = cardapio.stream().filter(i -> i.getIdItem() == idItem).findFirst().orElse(null);
//                        if (itemPedido != null) {
//                            pedido.adicionarPedido(itemPedido);
//                            pedidos.add(pedido);
//                        } else {
//                            System.out.println("Item não encontrado.");
//                        }
//                    } else {
//                        System.out.println("Mesa não está ocupada ou não encontrada.");
//                    }
                    break;

                case "4":
                    System.out.print("Digite o número do pedido para enviar para cozinha: ");

                    Pedido.enviarCozinha();

//                    int numPedido = scanner.nextInt();
//                    Pedido pedido = pedidos.stream().filter(p -> p.getIdPedido() == numPedido).findFirst().orElse(null);
//                    if (pedido != null) {
//                        System.out.println("Pedido enviado para a cozinha.");
//                    } else {
//                        System.out.println("Pedido não encontrado.");
//                    }
                    break;

                case "5":
                    System.out.print("Digite o número da mesa para efetuar pagamento: ");
                    int numMesa3 = scanner.nextInt();
                    DatabaseInsertExample.desocuparMesa(numMesa3);
                    break;

                case "6":
                    System.out.println("--- Gerenciar Cardápio ---");
                    System.out.println("1. Adicionar Item");
                    System.out.println("2. Remover Item");
                    System.out.println("3. Listar Cardápio\n");
                    System.out.print("Escolha uma opção: ");

                    ItemCardapio.gerenciarCardapio();
//                    String opcaoCardapio = scanner.nextLine();
//
//                    switch (opcaoCardapio) {
//                        case "1":
//                            System.out.print("Digite o nome do item: ");
//                            String nome = scanner.next();
//                            System.out.print("Digite a descrição do item: ");
//                            String descricao = scanner.next();
//                            System.out.print("Digite o preço do item: ");
//                            double preco = scanner.nextDouble();
//                            cardapio.add(new ItemCardapio(cardapio.size() + 1, nome, descricao, preco));
//                            DatabaseInsertExample.insertDataCardapio(0,nome, descricao, preco);
//                            System.out.println("Item adicionado ao cardápio.");
//                            scanner.nextLine();
//                            break;
//
//                        case "2":
//                            DatabaseInsertExample.listarDataCardapio();
//                            System.out.print("Digite o ID do item para remover: (caso não haja nenhum, clique 0): ");
//                            int idItem = scanner.nextInt();
//                            if (idItem == 0){
//                                break;
//                            } else {
//                                DatabaseInsertExample.removeDataCardapio(idItem);
//                                scanner.nextLine();
//                            }
//                            break;
//                        case "3":
//                            DatabaseInsertExample.listarDataCardapio();
//                    }
                    break;

                case "7":
                    colaborador.gerarRelatorioPedidos(pedidos);
                    break;

                case "8":
                    Pedido.gerenciarPedido(scanner,pedidos,cardapio);
                    break;
                case "9":
                    colaborador.sair();
                    break;

                default:
                    break;
            }
        } while (!opcao.equals("9"));
    }
}