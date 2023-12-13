package service;

import DAO.ItemOrdemServicoDAO;
import model.ItemOrdemServico;

import java.util.List;

public class ItemOrdemServicoService {
    private ItemOrdemServicoDAO itemOrdemServicoDAO = new ItemOrdemServicoDAO();
    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertItemOrdemServico(ItemOrdemServico entidade) {
        itemOrdemServicoDAO.insertItemOrdemServico(entidade);
    }
    public void salvar(String descricao, long preco, int idOrdemServico) {
        ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
        itemOrdemServico.setDescricao(descricao);
        itemOrdemServico.setPreco(preco);
        itemOrdemServico.setIdOrdemServico(idOrdemServico);
        itemOrdemServicoDAO.insertItemOrdemServico(itemOrdemServico);
    }
    public void buscarPorId(int id) {
        ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
        itemOrdemServico = itemOrdemServicoDAO.selectItemOrdemServico(id);
        System.out.println(itemOrdemServico);
    }

    public void atualizar(int id, String descricao, long preco, int idOrdemServico) {
        ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
        itemOrdemServico = itemOrdemServicoDAO.selectItemOrdemServico(id);
        itemOrdemServico.setDescricao(descricao);
        itemOrdemServico.setPreco(preco);
        itemOrdemServico.setIdOrdemServico(idOrdemServico);
        itemOrdemServicoDAO.updateItemOrdemServico(itemOrdemServico);
        itemOrdemServico = itemOrdemServicoDAO.selectItemOrdemServico(id);
        System.out.println(itemOrdemServico);
    }

    public void selecionarTodos() {
        List<ItemOrdemServico> itensOrdemServico = itemOrdemServicoDAO.selectAllItensOrdemServico();
        itensOrdemServico.forEach(System.out::println);
    }

    public void deletar(int id){
        itemOrdemServicoDAO.deleteItemOrdemServico(1);
        itemOrdemServicoDAO.selectAllItensOrdemServico().forEach(System.out::println);
    }
}
