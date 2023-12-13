package util;
import service.UfService;
import service.CidadeService;
import service.ClienteService;
import service.EmpresaService;
import service.EnderecoService;
import service.ItemOrdemServicoService;
import service.OrdemServicoService;
public class Teste {
    public static void main(String[] args){
        UfService testeUf = new UfService();
        CidadeService testeCidade = new CidadeService();
        ClienteService testeCliente = new ClienteService();
        EmpresaService testeEmpresa = new EmpresaService();
        EnderecoService testeEndereco = new EnderecoService();
        OrdemServicoService testeOrdemServico = new OrdemServicoService();
        ItemOrdemServicoService testeItemOrdemServico = new ItemOrdemServicoService();

        //testeUf.salvar("teste uf", 239543);
        testeOrdemServico.selecionarTodos();
    }
}
