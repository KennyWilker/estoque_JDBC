package br.com.kenny.estoque.model;
/**
 *
 * @author kenny
 */
public class Produto {
    
    private int id;
    private String descricao;
    private int quantidade;
    private double preco;

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

   
    @Override
    public String toString() {
        return getDescricao();
    }
}
