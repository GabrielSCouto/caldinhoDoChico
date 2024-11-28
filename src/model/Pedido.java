package model;

import dataBase.*;
import view.Menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Pedido {
    static Scanner scanner = new Scanner(System.in);

    private int idPedido;
    private static List<ItemCardapio> itens;


    public static List<Pedido> pedidos = new ArrayList<>();


    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        itens = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    //PODE SER MESMO METODO DE FAZER PEDIDO????
    public void adicionarPedido(ItemCardapio item) {
        itens.add(item);
        System.out.println("Item adicionado ao pedido: " + item.getNome());
    }
    public void removerPedido(ItemCardapio item) {
        itens.remove(item);
        System.out.println("Item removido do pedido: " + item.getNome());
    }

//    public static void listarItens() {
//        System.out.println("Itens no pedido:");
//        for (ItemCardapio item : itens) {
//            System.out.println("- " + item.getNome() + " (R$" + item.getPreco() + ")");
//        }
//    }

//    public static void gerenciarPedido(Scanner scanner, List<Pedido> pedidos, List<ItemCardapio> cardapio) throws SQLException {
//        System.out.print("Digite o número do pedido que deseja gerenciar: ");
//        int idPedido = scanner.nextInt();
//        Pedido pedido = pedidos.stream().filter(p -> p.getIdPedido() == idPedido).findFirst().orElse(null);
//
//        if (pedido == null) {
//            System.out.println("Pedido não encontrado.");
//            Menu.menu();
//        }
//
//        System.out.println("\n--- Gerenciar Pedido ---");
//        System.out.println("1. Adicionar Item ao Pedido");
//        System.out.println("2. Remover Item do Pedido");
//        System.out.println("3. Apagar Pedido");
//        System.out.print("Escolha uma opção: ");
//        String opcao = scanner.nextLine();
//
//        switch (opcao) {
//            case "1":
//                System.out.println("Itens disponíveis no cardápio:");
//                ListData.listarDataCardapio();
//                System.out.print("Digite o ID do item para adicionar: ");
//                int idItemAdicionar = scanner.nextInt();
//                ItemCardapio itemAdicionar = cardapio.stream().filter(i -> i.getIdItem() == idItemAdicionar).findFirst().orElse(null);
//                if (itemAdicionar != null) {
//                    assert pedido != null;
//                    pedido.adicionarPedido(itemAdicionar);
//                } else {
//                    System.out.println("Item não encontrado.");
//                }
//                break;
//
//            case "2":
//                System.out.println("Itens no pedido:");
//                ListData.listarDataPedidos();
//                System.out.print("Digite o ID do item para remover: ");
//                int idItemRemover = scanner.nextInt();
//                assert pedido != null;
//                ItemCardapio itemRemover = pedido.getItens().stream().filter(i -> i.getIdItem() == idItemRemover).findFirst().orElse(null);
//                if (itemRemover != null) {
//                    pedido.removerPedido(itemRemover);
//                } else {
//                    System.out.println("Item não encontrado no pedido.");
//                }
//                break;
//
//            case "3":
//                pedidos.remove(pedido);
//                System.out.println("Pedido apagado com sucesso.");
//                break;
//
//            default:
//                System.out.println("Opção inválida.");
//        }
//    }
    //switch 3 no menu
//    public static void fazerPedido() throws SQLException {
//        int numMesa2 = scanner.nextInt();
//        Mesa mesaSelecionada1 = mesas.stream().filter(m -> m.getNumero() == numMesa2).findFirst().orElse(null);
//        if (mesaSelecionada1 != null && !mesaSelecionada1.isLivre()) {
//            Pedido pedido = new Pedido(pedidos.size() + 1);
//            System.out.println("Itens no cardápio:");
//            listarDataCardapio();
//            System.out.print("Digite o ID do item para adicionar ao pedido: ");
//            int idItem = scanner.nextInt();
//            ItemCardapio itemPedido = cardapio.stream().filter(i -> i.getIdItem() == idItem).findFirst().orElse(null);
//            if (itemPedido != null) {
//                pedido.adicionarPedido(itemPedido);
//                pedidos.add(pedido);
//                enviarCozinha();
//            } else {
//                System.out.println("Item não encontrado.");
//            }
//        } else {
//            System.out.println("Mesa não está ocupada ou não encontrada.");
//        }
//    }

    public static void gerenciarPedido(Scanner scanner) {
        System.out.print("Digite o número do pedido que deseja gerenciar: ");
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

        switch (opcao) {
            case "1":
                ListData.listarItensPedido(idPedido);
                System.out.print("Digite o ID do item que deseja substituir: ");
                int idItemAntigo = scanner.nextInt();
                System.out.print("Digite o ID do novo item: ");
                int idItemNovo = scanner.nextInt();
                updateData.atualizarItensDoPedido(idPedido, idItemAntigo, idItemNovo);
                scanner.nextLine();
                break;

            case "2":
                RemoveData.removeDataPedido(idPedido);
                scanner.nextLine();
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }


    public static void fazerPedido() throws SQLException {
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