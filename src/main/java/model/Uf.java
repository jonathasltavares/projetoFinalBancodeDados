package model;

public class Uf {
    private int id;
    private String descricao;
    private int codigo;

    public Uf(){

    }
    public Uf(int id, String descricao, int codigo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Uf{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", codigo=" + codigo +
                '}';
    }
}
