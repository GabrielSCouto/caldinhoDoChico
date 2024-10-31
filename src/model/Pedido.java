package model;

import view.Menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Pedido {
    private int idPedido;
    private Date dataHora;
    private String status;  // "Em andamento", "Concluido"
    private static List<ItemCardapio> itens;

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

    public static void gerenciarPedido(Scanner scanner, List<Pedido> pedidos, List<ItemCardapio> cardapio) {
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
}