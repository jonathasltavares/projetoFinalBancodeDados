package DAO;

import model.OrdemServico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoDAO extends ConexaoDB {

    private static final String INSERT_ORDEM_SERVICO_SQL = "INSERT INTO ordem_servico (observacao, dt_abertura, dt_saida, username_responsavel, id_cliente, id_empresa) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ORDEM_SERVICO_BY_ID = "SELECT id, observacao, dt_abertura, dt_saida, username_responsavel, id_cliente, id_empresa FROM ordem_servico WHERE id = ?";
    private static final String SELECT_ALL_ORDEM_SERVICO = "SELECT * FROM ordem_servico;";
    private static final String DELETE_ORDEM_SERVICO_SQL = "DELETE FROM ordem_servico WHERE id = ?;";
    private static final String UPDATE_ORDEM_SERVICO_SQL = "UPDATE ordem_servico SET observacao = ?, dt_abertura = ?, dt_saida = ?, username_responsavel = ?, id_cliente = ?, id_empresa = ? WHERE id = ?;";
    private static final String TOTAL_ORDEM_SERVICO = "SELECT count(1) FROM ordem_servico;";

    public Integer countOrdemServico() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(TOTAL_ORDEM_SERVICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertOrdemServico(OrdemServico ordemServico) {
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(INSERT_ORDEM_SERVICO_SQL)) {
            preparedStatement.setString(1, ordemServico.getObservacao());
            preparedStatement.setTimestamp(2, ordemServico.getDtAbertura());
            preparedStatement.setTimestamp(3, ordemServico.getDtSaida());
            preparedStatement.setString(4, ordemServico.getUsernameResponsavel());
            preparedStatement.setInt(5, ordemServico.getIdCliente());
            preparedStatement.setInt(6, ordemServico.getIdEmpresa());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public OrdemServico selectOrdemServico(int id) {
        OrdemServico ordemServico = null;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ORDEM_SERVICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String observacao = rs.getString("observacao");
                java.sql.Timestamp dtAbertura = rs.getTimestamp("dt_abertura");
                java.sql.Timestamp dtSaida = rs.getTimestamp("dt_saida");
                String usernameResponsavel = rs.getString("username_responsavel");
                int idCliente = rs.getInt("id_cliente");
                int idEmpresa = rs.getInt("id_empresa");
                ordemServico = new OrdemServico(id, observacao, dtAbertura, dtSaida, usernameResponsavel, idCliente, idEmpresa);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ordemServico;
    }

    public List<OrdemServico> selectAllOrdensServico() {
        List<OrdemServico> ordensServico = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ALL_ORDEM_SERVICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String observacao = rs.getString("observacao");
                java.sql.Timestamp dtAbertura = rs.getTimestamp("dt_abertura");
                java.sql.Timestamp dtSaida = rs.getTimestamp("dt_saida");
                String usernameResponsavel = rs.getString("username_responsavel");
                int idCliente = rs.getInt("id_cliente");
                int idEmpresa = rs.getInt("id_empresa");
                ordensServico.add(new OrdemServico(id, observacao, dtAbertura, dtSaida, usernameResponsavel, idCliente, idEmpresa));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ordensServico;
    }

    public boolean deleteOrdemServico(int id) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(DELETE_ORDEM_SERVICO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrdemServico(OrdemServico ordemServico) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(UPDATE_ORDEM_SERVICO_SQL)) {
            statement.setString(1, ordemServico.getObservacao());
            statement.setTimestamp(2, ordemServico.getDtAbertura());
            statement.setTimestamp(3, ordemServico.getDtSaida());
            statement.setString(4, ordemServico.getUsernameResponsavel());
            statement.setInt(5, ordemServico.getIdCliente());
            statement.setInt(6, ordemServico.getIdEmpresa());
            statement.setInt(7, ordemServico.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
