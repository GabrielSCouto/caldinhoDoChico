package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe model.Mesa
public class Mesa {
    private static int numMesa;
    private static boolean livre;
    private static boolean pagamentoEfetuado;

    static List<Mesa> mesas = new ArrayList<>();


    public Mesa(int numero) {
        this.numMesa = numero;
        this.livre = true;
        this.pagamentoEfetuado = false;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public boolean isLivre() {
        return livre;
    }


    // MÉTODOS

    public static void ocuparMesa(){

    Scanner scanner = new Scanner(System.in);

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

        for (Mesa mesa : mesas) {
            String status = mesa.isLivre() ? "Livre" : "Ocupada";
            System.out.println("Mesa " + mesa.getNumMesa() + " - " + status);
        }
        System.out.print("Digite o número da mesa para ocupar: \n");
        numMesa = scanner.nextInt();
        Mesa mesaSelecionada = mesas.stream().filter(m -> m.getNumMesa() == numMesa).findFirst().orElse(null);
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

        scanner.close();
    }

    public static void listarMesas(){
        System.out.println("Listagem de Mesas:");
        for (Mesa mesa : mesas) {
            String status = mesa.isLivre() ? "Livre" : "Ocupada";
            System.out.println("Mesa " + mesa.getNumMesa() + " - " + status);
        }
    }


    public void alterarStatus(boolean livre) {
        this.livre = livre;
    }

    public static void efetuarPagamento() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número da mesa para efetuar pagamento: ");
        numMesa = scanner.nextInt();
        Mesa mesaSelecionada = mesas.stream().filter(m -> m.getNumMesa() == numMesa).findFirst().orElse(null);
        if (mesaSelecionada != null && !mesaSelecionada.isLivre()) {
            mesaSelecionada.efetuarPagamento();
            String status = mesaSelecionada.isLivre() ? "Livre" : "Ocupada";
            System.out.println("Status atualizado: Mesa " + numMesa + " está agora " + status);
        } else {
            System.out.println("Mesa não encontrada ou está livre.");
        }

        if (!pagamentoEfetuado) {
            System.out.println("Pagamento efetuado (ou mesa sem pedidos).");
            pagamentoEfetuado = true;
            livre = true;
        } else {
            System.out.println("Pagamento já foi efetuado para esta mesa.");
        }
    }
}