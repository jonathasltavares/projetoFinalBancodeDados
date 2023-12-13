package service;

import DAO.UfDAO;
import model.Uf;

import java.util.List;

public class UfService {
        private UfDAO ufDAO = new UfDAO();
        //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
        // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
        public void insertUf(Uf entidade) {
                ufDAO.insertUf(entidade);
        }

        public void salvar(String descricao, int codigo){
                Uf uf = new Uf();
                uf.setDescricao(descricao);
                uf.setCodigo(codigo);
                ufDAO.insertUf(uf);
        }

        //
        public void buscarPorId(int id) {
                Uf uf = new Uf();
                uf = ufDAO.selectUf(id);
                System.out.println(uf);
        }
        public void atualizar(int id, String descricao, int codigo){
                Uf uf = new Uf();
                uf = ufDAO.selectUf(4);
                uf.setDescricao(descricao);
                uf.setCodigo(33);
                ufDAO.updateUf(uf);
                uf = ufDAO.selectUf(4);
                System.out.println(uf);
        }

        public void selecionarTodos(){
                List<Uf> ufs = ufDAO.selectAllUfs();
                ufs.forEach(System.out::println);
        }

        public void deletar(){
                ufDAO.deleteUf(35);
                ufDAO.selectAllUfs().forEach(System.out::println);
        }

}
