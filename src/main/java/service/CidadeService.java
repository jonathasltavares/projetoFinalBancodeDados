package service;

import DAO.CidadeDAO;
import model.Cidade;

import java.util.List;

public class CidadeService {
    private CidadeDAO cidadeDAO = new CidadeDAO();
    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertCidade(Cidade entidade) {
        cidadeDAO.insertCidade(entidade);
    }
    public void salvar(String descricao, int codigo, int idUf) {
        Cidade cidade = new Cidade();
        cidade.setDescricao(descricao);
        cidade.setCodigo(codigo);
        cidade.setIdUf(idUf);
        cidadeDAO.insertCidade(cidade);
    }

    public void buscarPorId(int id) {
        Cidade cidade = new Cidade();
        cidade = cidadeDAO.selectCidade(id);
        System.out.println(cidade);
    }

    public void atualizar(int id, String descricao, int codigo, int idUf) {
        Cidade cidade = new Cidade();
        cidade = cidadeDAO.selectCidade(id);
        cidade.setDescricao(descricao);
        cidade.setCodigo(codigo);
        cidade.setIdUf(idUf);
        cidadeDAO.updateCidade(cidade);
        cidade = cidadeDAO.selectCidade(id);
        System.out.println(cidade);

    }

    public void selecionarTodos() {
        List<Cidade> cidades = cidadeDAO.selectAllCidades();
        cidades.forEach(System.out::println);
    }

    public void deletar(int id){
        cidadeDAO.deleteCidade(id);
        cidadeDAO.selectAllCidades().forEach(System.out::println);
    }
}
