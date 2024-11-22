package model;

// Classe model.Mesa
public class Mesa {
    private int numero;
    private boolean livre;
    private boolean pagamentoEfetuado;

    public Mesa(int numero) {
        this.numero = numero;
        this.livre = true;
        this.pagamentoEfetuado = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isLivre() {
        return livre;
    }

    public void alterarStatus(boolean livre) {
        this.livre = livre;
    }


    //MUDAR PARA PEDIDO???
    public void efetuarPagamento() {
        if (!pagamentoEfetuado) {
            System.out.println("Pagamento efetuado (ou mesa sem pedidos).");
            this.pagamentoEfetuado = true;
            this.livre = true;
        } else {
            System.out.println("Pagamento já foi efetuado para esta mesa.");
        }
    }
}