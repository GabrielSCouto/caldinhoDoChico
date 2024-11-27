package model;

import dataBase.DataBaseConnection;
import dataBase.*;
import view.Menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static model.ItemCardapio.cardapio;


public class Pedido {
    static Scanner scanner = new Scanner(System.in);

    private int idPedido;
    private Date dataHora;
    private String status;  // "Em andamento", "Concluido"
    private static List<ItemCardapio> itens;

    // estavam em MENU:
    public static List<Mesa> mesas = new ArrayList<>();
    public static List<Pedido> pedidos = new ArrayList<>();


    public Pedido(int idPedido, Date dataHora, String status) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.status = status;
        itens = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getStatus() {
        return status;
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

    public static void listarItens() {
        System.out.println("Itens no pedido:");
        for (ItemCardapio item : itens) {
            System.out.println("- " + item.getNome() + " (R$" + item.getPreco() + ")");
        }
    }

    public static void gerenciarPedido(Scanner scanner, List<Pedido> pedidos, List<ItemCardapio> cardapio) throws SQLException {
        System.out.print("Digite o número do pedido que deseja gerenciar: ");
        int idPedido = scanner.nextInt();
        Pedido pedido = pedidos.stream().filter(p -> p.getIdPedido() == idPedido).findFirst().orElse(null);

        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            Menu.menu();
        }

        System.out.println("\n--- Gerenciar Pedido ---");
        System.out.println("1. Adicionar Item ao Pedido");
        System.out.println("2. Remover Item do Pedido");
        System.out.println("3. Apagar Pedido");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                System.out.println("Itens disponíveis no cardápio:");
                for (ItemCardapio item : cardapio) {
                    System.out.println(item.getIdItem() + ". " + item.getNome() + " - R$" + item.getPreco());
                }
                System.out.print("Digite o ID do item para adicionar: ");
                int idItemAdicionar = scanner.nextInt();
                ItemCardapio itemAdicionar = cardapio.stream().filter(i -> i.getIdItem() == idItemAdicionar).findFirst().orElse(null);
                if (itemAdicionar != null) {
                    assert pedido != null;
                    pedido.adicionarPedido(itemAdicionar);
                } else {
                    System.out.println("Item não encontrado.");
                }
                break;

            case "2":
                System.out.println("Itens no pedido:");
                listarItens();
                System.out.print("Digite o ID do item para remover: ");
                int idItemRemover = scanner.nextInt();
                assert pedido != null;
                ItemCardapio itemRemover = pedido.getItens().stream().filter(i -> i.getIdItem() == idItemRemover).findFirst().orElse(null);
                if (itemRemover != null) {
                    pedido.removerPedido(itemRemover);
                } else {
                    System.out.println("Item não encontrado no pedido.");
                }
                break;

            case "3":
                pedidos.remove(pedido);
                System.out.println("Pedido apagado com sucesso.");
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

    //switch 3 no menu
    public static void fazerPedido() throws SQLException {
        int numMesa2 = scanner.nextInt();
        Mesa mesaSelecionada1 = mesas.stream().filter(m -> m.getNumero() == numMesa2).findFirst().orElse(null);
        if (mesaSelecionada1 != null && !mesaSelecionada1.isLivre()) {
            Pedido pedido = new Pedido(pedidos.size() + 1, new Date(), "Em andamento");
            System.out.println("Itens no cardápio:");
            ListData.listarDataCardapio();
            System.out.print("Digite o ID do item para adicionar ao pedido: ");
            int idItem = scanner.nextInt();
            ItemCardapio itemPedido = cardapio.stream().filter(i -> i.getIdItem() == idItem).findFirst().orElse(null);
            if (itemPedido != null) {
                pedido.adicionarPedido(itemPedido);
                pedidos.add(pedido);
                enviarCozinha();
            } else {
                System.out.println("Item não encontrado.");
            }
        } else {
            System.out.println("Mesa não está ocupada ou não encontrada.");
        }
    }

    public static void enviarCozinha() {
            System.out.println("Pedido enviado para a cozinha.");
    }


}