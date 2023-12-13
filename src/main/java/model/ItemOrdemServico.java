package model;

public class ItemOrdemServico {
    private int id;
    private String descricao;
    private long preco;
    private int idOrdemServico; // Representa a referência para a tabela ordem_servico

    public ItemOrdemServico(){}

    public ItemOrdemServico(int id, String descricao, Long preco, int idOrdemServico) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.idOrdemServico = idOrdemServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    public int getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(int idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    @Override
    public String toString() {
        return "ItemOrdemServico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", idOrdemServico=" + idOrdemServico +
                '}';
    }
}
