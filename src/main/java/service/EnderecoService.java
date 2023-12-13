package service;


import DAO.EnderecoDAO;
import model.Endereco;

import java.util.List;

public class EnderecoService {
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertEndereco(Endereco entidade) {
        enderecoDAO.insertEndereco(entidade);
    }
    public void salvar(String rua, String numero, String bairro, String cep, int idCidade) {
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setIdCidade(idCidade);
        enderecoDAO.insertEndereco(endereco);
    }
    public void buscarPorId(int id) {
        Endereco endereco = new Endereco();
        endereco = enderecoDAO.selectEndereco(id);
        System.out.println(endereco);
    }

    public void atualizar(int id, String rua, String numero, String bairro, String cep, int idCidade) {
        Endereco endereco = new Endereco();
        endereco = enderecoDAO.selectEndereco(id);
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setIdCidade(idCidade);
        enderecoDAO.updateEndereco(endereco);
        endereco = enderecoDAO.selectEndereco(id);
        System.out.println(endereco);
    }

    public void selecionarTodos() {
        List<Endereco> enderecos = enderecoDAO.selectAllEnderecos();
        enderecos.forEach(System.out::println);
    }

    public void deletar(int id) {
        enderecoDAO.deleteEndereco(id);
        enderecoDAO.selectAllEnderecos().forEach(System.out::println);
    }
}
