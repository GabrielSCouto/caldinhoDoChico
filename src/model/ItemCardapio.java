package model;

import dataBase.DataBaseConnection;
import dataBase.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe model.ItemCardapio
public class ItemCardapio {
    static Scanner scanner = new Scanner(System.in);

    //estava em menu
    public static List<ItemCardapio> cardapio = new ArrayList<>();


    private int idItem;
    private String nome;
    private String descricao;
    private double preco;

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

    public void alterarPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    public void alterarDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }


    //switch 6 no menu
    public static void gerenciarCardapio() throws SQLException {
        String opcaoCardapio = scanner.nextLine();

        switch (opcaoCardapio) {
            case "1":
                System.out.print("Digite o nome do item: ");
                String nome = scanner.nextLine();
                System.out.print("Digite a descrição do item: ");
                String descricao = scanner.nextLine();
                System.out.print("Digite o preço do item: ");
                double preco = scanner.nextDouble();
                cardapio.add(new ItemCardapio(cardapio.size() + 1, nome, descricao, preco));
                InsertData.insertDataCardapio(0,nome, descricao, preco);
                System.out.println("Item adicionado ao cardápio.");
                scanner.nextLine();
                break;

            case "2":
                ListData.listarDataCardapio();
                System.out.print("Digite o ID do item para remover: (caso não haja nenhum, clique 0): ");
                int idItem = scanner.nextInt();
                if (idItem == 0){
                    break;
                } else {
                    RemoveData.removeDataCardapio(idItem);
                    scanner.nextLine();
                }
                break;
            case "3":
                ListData.listarDataCardapio();
        }
    }
}