package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Date dataHora;
    private String status;  // "Em andamento", "Concluido"
    private List<ItemCardapio> itens;

    public Pedido(int idPedido, Date dataHora, String status) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.status = status;
        this.itens = new ArrayList<>();
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
}