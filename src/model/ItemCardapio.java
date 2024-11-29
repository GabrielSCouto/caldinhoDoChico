package model;

import dataBase.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Classe model.ItemCardapio
public class ItemCardapio {
    static Scanner scanner = new Scanner(System.in);

    // switch 6 no menu
    public static void gerenciarCardapio() throws SQLException {
        try {
            String opcaoCardapio = scanner.nextLine(); // Lê a opção escolhida no menu do cardápio
            int idItem;
            switch (opcaoCardapio) {
                case "1":
                    try {
                        // Solicita os detalhes do item a ser adicionado
                        System.out.print("Digite o nome do item: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite a descrição do item: ");
                        String descricao = scanner.nextLine();
                        System.out.print("Digite o preço do item: ");
                        double preco = scanner.nextDouble();
                        InsertData.insertDataCardapio(0, nome, descricao, preco);
                        System.out.println("Item adicionado ao cardápio.");
                        scanner.nextLine(); // Limpa o buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida para o preço. Certifique-se de digitar um número.");
                        scanner.nextLine(); // Limpa o buffer após erro
                    }
                    break;

                case "2":
                    // Exibe os itens do cardápio e solicita o ID do item a ser removido
                    ListData.listarDataCardapio();
                    try {
                        System.out.print("Digite o ID do item para remover: (caso não haja nenhum, clique 0): ");
                        idItem = scanner.nextInt();
                        if (idItem == 0) {
                            break;
                        } else {
                            RemoveData.removeDataCardapio(idItem);
                            scanner.nextLine(); // Limpa o buffer após operação
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida para o ID. Certifique-se de digitar um número inteiro.");
                        scanner.nextLine(); // Limpa o buffer após erro
                    }
                    break;

                case "3":
                    try {
                        // Solicita os dados para atualizar o item do cardápio
                        System.out.print("Digite o ID do item a ser atualizado: ");
                        idItem = scanner.nextInt();
                        scanner.nextLine(); // Limpa o buffer

                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Digite a nova descrição: ");
                        String novaDescricao = scanner.nextLine();

                        System.out.print("Digite o novo preço: ");
                        double novoPreco = scanner.nextDouble();

                        // Chama o método para atualizar no banco
                        updateData.atualizarItemCardapio(idItem, novoNome, novaDescricao, novoPreco);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida para ID ou preço. Certifique-se de digitar números corretamente.");
                        scanner.nextLine(); // Limpa o buffer após erro
                    }
                    break;

                case "4":
                    // Lista todos os itens do cardápio
                    ListData.listarDataCardapio();
                    break;

                default:
                    System.out.println("Opção inválida. Escolha uma opção válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de digitar as informações corretamente.");
            scanner.nextLine(); // Limpa o buffer após erro geral
        }
    }
}
