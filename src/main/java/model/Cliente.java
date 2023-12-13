package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Cliente {
    private int id;
    private String nome;
    private Date dtNascimento;
    private String cpf;
    private String email;
    private int idEndereco; // Representa a referência para a tabela endereco

    public Cliente(){}


    public Cliente(int id, String nome, Date dtNascimento, String cpf, String email, int idEndereco) {
        this.id = id;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
        this.email = email;
        this.idEndereco = idEndereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        if (dtNascimento == null || dtNascimento.isEmpty()) {
            // Adicione a lógica desejada para tratar datas nulas ou vazias
            return;
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        try {
            java.util.Date utilDate = formato.parse(dtNascimento);
            this.dtNascimento = new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Adicione a lógica desejada para lidar com a exceção (ex: imprimir mensagem de erro)
            System.err.println("Erro ao converter a data: " + e.getMessage());
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", idEndereco=" + idEndereco +
                '}';
    }
}
