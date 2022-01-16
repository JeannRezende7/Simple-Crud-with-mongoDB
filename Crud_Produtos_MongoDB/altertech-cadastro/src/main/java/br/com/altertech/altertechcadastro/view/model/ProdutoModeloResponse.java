package br.com.altertech.altertechcadastro.view.model;

public class ProdutoModeloResponse {
    private String id;
    private String nome;
    private double valor,preco,codigo,quantidadeEstoque;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getCodigo() {
        return codigo;
    }
    public void setCodigo(double codigo) {
        this.codigo = codigo;
    }
    public double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }   
}
