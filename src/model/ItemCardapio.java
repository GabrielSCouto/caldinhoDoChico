package model;

import dataBase.*;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Classe model.ItemCardapio
public class ItemCardapio {
    static Scanner scanner = new Scanner(System.in);

    //switch 6 no menu
    public static void gerenciarCardapio() throws SQLException, InputMismatchException {
        String opcaoCardapio = scanner.nextLine();
        int idItem;
        switch (opcaoCardapio) {
            case "1":
                System.out.print("Digite o nome do item: ");
                String nome = scanner.nextLine();
                System.out.print("Digite a descrição do item: ");
                String descricao = scanner.nextLine();
                System.out.print("Digite o preço do item: ");
                double preco = scanner.nextDouble();
                InsertData.insertDataCardapio(0,nome, descricao, preco);
                System.out.println("Item adicionado ao cardápio.");
                scanner.nextLine();
                break;

            case "2":
                ListData.listarDataCardapio();
                System.out.print("Digite o ID do item para remover: (caso não haja nenhum, clique 0): ");
                idItem = scanner.nextInt();
                if (idItem == 0){
                    break;
                } else {
                    RemoveData.removeDataCardapio(idItem);
                    scanner.nextLine();
                }
                break;
            case "3":
                System.out.print("Digite o ID do item a ser atualizado: ");
                idItem = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                System.out.print("Digite o novo nome: ");
                String novoNome = scanner.nextLine();

                System.out.print("Digite a nova descrição: ");
                String novaDescricao = scanner.nextLine();

                System.out.print("Digite o novo preço: ");
                double novoPreco = scanner.nextDouble();

                // Chama o método para atualizar no banco
                updateData.atualizarItemCardapio(idItem, novoNome, novaDescricao, novoPreco);
                break;
            case "4":
                ListData.listarDataCardapio();
        }
    }
}