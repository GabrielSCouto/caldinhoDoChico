package model;

// Classe model.ItemCardapio
public class ItemCardapio {
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
}