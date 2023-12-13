package model;

public class Cidade {
    private int id;
    private String descricao;
    private int codigo;
    private int idUf; // Representa a referência para a tabela uf

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cidade(){

    }
    public Cidade(int id, String descricao, int codigo, int idUf) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.idUf = idUf;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdUf() {
        return idUf;
    }

    public void setIdUf(int idUf) {
        this.idUf = idUf;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", codigo=" + codigo +
                ", idUf=" + idUf +
                '}';
    }

}
