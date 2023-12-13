package service;

import DAO.OrdemServicoDAO;
import model.OrdemServico;

import java.sql.Timestamp;
import java.util.List;

public class OrdemServicoService {
    private OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertOrdemServico(OrdemServico entidade) {
        ordemServicoDAO.insertOrdemServico(entidade);
    }

    public void salvar(String observacao, String userNameResponsavel, int idCliente, int idEmpresa) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setObservacao(observacao);
        ordemServico.setDtAbertura(new Timestamp(System.currentTimeMillis()));
        ordemServico.setDtSaida(null);
        ordemServico.setUsernameResponsavel(userNameResponsavel);
        ordemServico.setIdCliente(idCliente);
        ordemServico.setIdEmpresa(idEmpresa);
        ordemServicoDAO.insertOrdemServico(ordemServico);
    }

    public void buscarPorId(int id) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico = ordemServicoDAO.selectOrdemServico(id);
        System.out.println(ordemServico);
    }

    public void atualizar(int id, String observacao, String userNameResponsavel, int idCliente, int idEmpresa){
        OrdemServico ordemServico = new OrdemServico();
        ordemServico = ordemServicoDAO.selectOrdemServico(id);
        ordemServico.setObservacao(observacao);
        ordemServico.setIdCliente(idCliente);
        ordemServico.setIdEmpresa(idEmpresa);
        ordemServico.setUsernameResponsavel(userNameResponsavel);
        ordemServicoDAO.updateOrdemServico(ordemServico);
        ordemServico = ordemServicoDAO.selectOrdemServico(id);
        System.out.println(ordemServico);
    }
    public void selecionarTodos() {
        List<OrdemServico> ordensServico = ordemServicoDAO.selectAllOrdensServico();
        ordensServico.forEach(System.out::println);
    }
    public void deletar(int id){
        ordemServicoDAO.deleteOrdemServico(id);
        ordemServicoDAO.selectAllOrdensServico().forEach(System.out::println);
    }
}
