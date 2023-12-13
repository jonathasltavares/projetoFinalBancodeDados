package service;

import DAO.ClienteDAO;
import model.Cliente;

import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertCliente(Cliente entidade) {
        clienteDAO.insertCliente(entidade);
    }
    public void salvar(String nome, String dtNascimento, String cpf, String email, int idEndereco){
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setDtNascimento(dtNascimento);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setIdEndereco(idEndereco);
        clienteDAO.insertCliente(cliente);
    }

    public void buscarPorId(int id) {
        Cliente cliente = new Cliente();
        cliente = clienteDAO.selectCliente(id);
        System.out.println(cliente);
    }

    public void atualizar(int id, String nome, String dtNascimento, String cpf, String email, int idEndereco) {
        Cliente cliente = new Cliente();
        cliente = clienteDAO.selectCliente(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setDtNascimento(dtNascimento);
        cliente.setIdEndereco(idEndereco);
        clienteDAO.updateCliente(cliente);
        cliente = clienteDAO.selectCliente(id);
        System.out.println(cliente);

    }
    public void selecionarTodos() {
        List<Cliente> clientes = clienteDAO.selectAllClientes();
        clientes.forEach(System.out::println);
    }

    public void deletar(int id) {
        clienteDAO.deleteCliente(2);
        clienteDAO.selectAllClientes().forEach(System.out::println);
    }
}
