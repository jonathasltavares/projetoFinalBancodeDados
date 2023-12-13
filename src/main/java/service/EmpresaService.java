package service;

import DAO.EmpresaDAO;
import model.Empresa;

import java.util.List;

public class EmpresaService {
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertEmpresa(Empresa entidade) {
        empresaDAO.insertEmpresa(entidade);
    }
    public void salvar(String nome, String cnpj, String slogan, int idEndereco) {
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia(nome);
        empresa.setCnpj(cnpj);
        empresa.setSlogan(slogan);
        empresa.setLogo(null);
        empresa.setIdEndereco(idEndereco);
        empresaDAO.insertEmpresa(empresa);
    }
    public void buscarPorId(int id) {
        Empresa empresa = new Empresa();
        empresa = empresaDAO.selectEmpresa(id);
        System.out.println(empresa);
    }
    public void atualizar(int id, String nome, String cnpj, String slogan, int idEndereco) {
        Empresa empresa = new Empresa();
        empresa = empresaDAO.selectEmpresa(id);
        empresa.setNomeFantasia(nome);
        empresa.setSlogan(slogan);
        empresa.setLogo(null);
        empresa.setIdEndereco(idEndereco);
        empresaDAO.updateEmpresa(empresa);
        empresa = empresaDAO.selectEmpresa(1);
        System.out.println(empresa);
    }
    public void selecionarTodos() {
        List<Empresa> empresas = empresaDAO.selectAllEmpresas();
        empresas.forEach(System.out::println);
    }

    public void deletar(int id) {
        empresaDAO.deleteEmpresa(1);
        empresaDAO.selectAllEmpresas().forEach(System.out::println);
    }
}
