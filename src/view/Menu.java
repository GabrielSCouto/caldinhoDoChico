package view;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
//        List<Mesa> mesas = new ArrayList<>();
//        List<ItemCardapio> cardapio = new ArrayList<>();
//        List<Pedido> pedidos = new ArrayList<>();

        Colaborador colaborador = new Colaborador();
        String opcao;

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
                    Mesa.ocuparMesa();
                    break;

                case "2":
                    Mesa.listarMesas();
                    break;

                case "3":
                    Pedido.fazerPedido();
                    break;

                case "4":
                    Pedido.enviarCozinha();
                    break;

                case "5":
                    Mesa.efetuarPagamento();
                    break;

                case "6":
                    ItemCardapio.gerenciarCardapio();
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
        } while (!opcao.equals("8"));

        scanner.close();
    }
}