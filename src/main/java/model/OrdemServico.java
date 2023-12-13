package model;

import java.sql.Timestamp;

public class OrdemServico {
    private int id;
    private String observacao;
    private Timestamp dtAbertura;
    private Timestamp dtSaida;
    private String usernameResponsavel;
    private int idCliente; // Representa a referência para a tabela cliente
    private int idEmpresa; // Representa a referência para a tabela empresa

    public OrdemServico(){}
    public OrdemServico(int id, String observacao, Timestamp dtAbertura, Timestamp dtSaida, String usernameResponsavel, int idCliente, int idEmpresa) {
        this.id = id;
        this.observacao = observacao;
        this.dtAbertura = dtAbertura;
        this.dtSaida = dtSaida;
        this.usernameResponsavel = usernameResponsavel;
        this.idCliente = idCliente;
        this.idEmpresa = idEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Timestamp getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(Timestamp dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public Timestamp getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(Timestamp dtSaida) {
        this.dtSaida = dtSaida;
    }

    public String getUsernameResponsavel() {
        return usernameResponsavel;
    }

    public void setUsernameResponsavel(String usernameResponsavel) {
        this.usernameResponsavel = usernameResponsavel;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String toString() {
        return "OrdemServico{" +
                "id=" + id +
                ", observacao='" + observacao + '\'' +
                ", dtAbertura=" + dtAbertura +
                ", dtSaida=" + dtSaida +
                ", usernameResponsavel='" + usernameResponsavel + '\'' +
                ", idCliente=" + idCliente +
                ", idEmpresa=" + idEmpresa +
                '}';
    }
}
