package model;

// Classe model.Colaborador
public class Colaborador {

//    public void gerarRelatorioPedidos(List<Pedido> pedidos) {
//        if (pedidos.isEmpty()) {
//            System.out.println("Nenhum pedido foi realizado. Relatório não pode ser gerado.");
//            return;
//        }
//
//        System.out.println("\n--- Relatório de Pedidos ---");
//        double valorTotalGeral = 0;
//
//        for (Pedido pedido : pedidos) {
//            System.out.println("Pedido ID: " + pedido.getIdPedido());
//            System.out.println("Itens do Pedido:");
//            double valorTotalPedido = 0;
//            for (ItemCardapio item : pedido.getItens()) {
//                System.out.println("- " + item.getNome() + " (R$" + item.getPreco() + ")");
//                valorTotalPedido += item.getPreco();
//            }
//            System.out.println("Valor total do pedido: R$" + valorTotalPedido);
//            System.out.println("------------------------------");
//            valorTotalGeral += valorTotalPedido;
//        }
//
//        System.out.println("Valor total de todos os pedidos: R$" + valorTotalGeral);
//        System.out.println("------------------------------");
//    }

    public void sair() {
        System.out.println("Saindo...");
    }
}