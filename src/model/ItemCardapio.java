package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe model.ItemCardapio
public class ItemCardapio {
    private int idItem;
    private String nome;
    private String descricao;
    private double preco;

    static List<ItemCardapio> cardapio = new ArrayList<>();

    public ItemCardapio(int idItem, String nome, String descricao, double preco) {
        this.idItem = idItem;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }


    public static void gerenciarCardapio(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Gerenciar Cardápio ---");
        System.out.println("1. Adicionar Item");
        System.out.println("2. Remover Item");
        System.out.println("3. Listar Cardápio\n");
        System.out.print("Escolha uma opção: ");
        String opcaoCardapio = scanner.nextLine();

        switch (opcaoCardapio) {

            case "1":
                System.out.print("Digite o nome do item: ");
                String nome = scanner.next();
                System.out.print("Digite a descrição do item: ");
                String descricao = scanner.next();
                System.out.print("Digite o preço do item: ");
                double preco = scanner.nextDouble();
                cardapio.add(new ItemCardapio(cardapio.size() + 1, nome, descricao, preco));
                System.out.println("Item adicionado ao cardápio.");
                break;

            case "2":
                if (!cardapio.isEmpty()) {
                    for (ItemCardapio item : cardapio) {
                        System.out.println(item.getIdItem() + ". " + item.getNome() + " - R$" + item.getPreco());
                    }
                }else if (cardapio.isEmpty()) {
                    System.out.println("\ncardapio está vazio\n");
                    break;
                }
                System.out.print("Digite o ID do item para remover: ");
                int idItem = scanner.nextInt();
                ItemCardapio itemRemover = cardapio.stream().filter(i -> i.getIdItem() == idItem).findFirst().orElse(null);
                if (itemRemover != null) {
                    cardapio.remove(itemRemover);
                    System.out.println("Item removido do cardápio.");
                } else {
                    System.out.println("Item não encontrado.");
                }
                break;

            case "3":
                if (!cardapio.isEmpty()) {
                    for (ItemCardapio item : cardapio) {
                        System.out.println(item.getIdItem() + ". " + item.getNome() + " - R$" + item.getPreco());
                    }
                }else if (cardapio.isEmpty()) {
                    System.out.println("\ncardapio está vazio\n");
                    break;
                }
        }
    }
    public void alterarPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    public void alterarDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }
}