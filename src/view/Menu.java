package view;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        List<Mesa> mesas = new ArrayList<>();
        List<ItemCardapio> cardapio = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

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

        Colaborador colaborador = new Colaborador();
        String opcao;

        do {
            System.out.println("\n--- view.Menu do Colaborador ---");
            System.out.println("1. Selecionar Mesa para ocupar");
            System.out.println("2. Visualizar Situação das Mesas");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Enviar Pedido para Cozinha");
            System.out.println("5. Efetuar Pagamento");
            System.out.println("6. Gerenciar Cardápio");
            System.out.println("7. Gerar Relatório de Pedidos");
            System.out.println("8. Sair\n");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    for (Mesa mesa : mesas) {
                        String status = mesa.isLivre() ? "Livre" : "Ocupada";
                        System.out.println("Mesa " + mesa.getNumero() + " - " + status);
                    }
                    System.out.print("Digite o número da mesa para ocupar: \n");
                    int numMesa = scanner.nextInt();
                    Mesa mesaSelecionada = mesas.stream().filter(m -> m.getNumero() == numMesa).findFirst().orElse(null);
                    if (mesaSelecionada != null) {
                        if (mesaSelecionada.isLivre()) {
                            System.out.println("Mesa " + numMesa + " selecionada.");
                            mesaSelecionada.alterarStatus(false);
                        } else {
                            System.out.println("Mesa ocupada.");
                        }
                    } else {
                        System.out.println("Mesa não encontrada.");
                    }
                    break;

                case "2":
                    System.out.println("Listagem de Mesas:");
                    for (Mesa mesa : mesas) {
                        String status = mesa.isLivre() ? "Livre" : "Ocupada";
                        System.out.println("Mesa " + mesa.getNumero() + " - " + status);
                    }
                    break;

                case "3":
                    System.out.print("Digite o número da mesa para fazer pedido: ");
                    numMesa = scanner.nextInt();
                    mesaSelecionada = mesas.stream().filter(m -> m.getNumero() == numMesa).findFirst().orElse(null);
                    if (mesaSelecionada != null && !mesaSelecionada.isLivre()) {
                        Pedido pedido = new Pedido(pedidos.size() + 1, new Date(), "Em andamento");
                        System.out.println("Itens no cardápio:");
                        for (ItemCardapio item : cardapio) {
                            System.out.println(item.getIdItem() + ". " + item.getNome() + " - R$" + item.getPreco());
                        }
                        System.out.print("Digite o ID do item para adicionar ao pedido: ");
                        int idItem = scanner.nextInt();
                        ItemCardapio itemPedido = cardapio.stream().filter(i -> i.getIdItem() == idItem).findFirst().orElse(null);
                        if (itemPedido != null) {
                            pedido.adicionarPedido(itemPedido);
                            pedidos.add(pedido);
                        } else {
                            System.out.println("Item não encontrado.");
                        }
                    } else {
                        System.out.println("Mesa não está ocupada ou não encontrada.");
                    }
                    break;

                case "4":
                    System.out.print("Digite o número do pedido para enviar para cozinha: ");
                    int numPedido = scanner.nextInt();
                    Pedido pedido = pedidos.stream().filter(p -> p.getIdPedido() == numPedido).findFirst().orElse(null);
                    if (pedido != null) {
                        System.out.println("Pedido enviado para a cozinha.");
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;

                case "5":
                    System.out.print("Digite o número da mesa para efetuar pagamento: ");
                    numMesa = scanner.nextInt();
                    mesaSelecionada = mesas.stream().filter(m -> m.getNumero() == numMesa).findFirst().orElse(null);
                    if (mesaSelecionada != null && !mesaSelecionada.isLivre()) {
                        mesaSelecionada.efetuarPagamento();
                        String status = mesaSelecionada.isLivre() ? "Livre" : "Ocupada";
                        System.out.println("Status atualizado: Mesa " + numMesa + " está agora " + status);
                    } else {
                        System.out.println("Mesa não encontrada ou está livre.");
                    }
                    break;

                case "6":
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
                    break;

                case "7":
                    colaborador.gerarRelatorioPedidos(pedidos);
                    break;

                case "8":
                    colaborador.sair();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("8"));

        scanner.close();
    }
}