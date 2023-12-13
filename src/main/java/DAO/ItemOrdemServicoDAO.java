package DAO;
import model.ItemOrdemServico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemOrdemServicoDAO extends ConexaoDB {

    private static final String INSERT_ITEM_ORDEM_SERVICO_SQL = "INSERT INTO item_ordem_servico (descricao, preco, id_ordem_servico) VALUES (?, ?, ?);";
    private static final String SELECT_ITEM_ORDEM_SERVICO_BY_ID = "SELECT id, descricao, preco, id_ordem_servico FROM item_ordem_servico WHERE id = ?";
    private static final String SELECT_ALL_ITEM_ORDEM_SERVICO = "SELECT * FROM item_ordem_servico;";
    private static final String DELETE_ITEM_ORDEM_SERVICO_SQL = "DELETE FROM item_ordem_servico WHERE id = ?;";
    private static final String UPDATE_ITEM_ORDEM_SERVICO_SQL = "UPDATE item_ordem_servico SET descricao = ?, preco = ?, id_ordem_servico = ? WHERE id = ?;";
    private static final String TOTAL_ITEM_ORDEM_SERVICO = "SELECT count(1) FROM item_ordem_servico;";

    public Integer countItemOrdemServico() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(TOTAL_ITEM_ORDEM_SERVICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertItemOrdemServico(ItemOrdemServico itemOrdemServico) {
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(INSERT_ITEM_ORDEM_SERVICO_SQL)) {
            preparedStatement.setString(1, itemOrdemServico.getDescricao());
            preparedStatement.setLong(2, itemOrdemServico.getPreco());
            preparedStatement.setInt(3, itemOrdemServico.getIdOrdemServico());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ItemOrdemServico selectItemOrdemServico(int id) {
        ItemOrdemServico itemOrdemServico = null;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ITEM_ORDEM_SERVICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                Long preco = rs.getLong("preco");
                int idOrdemServico = rs.getInt("id_ordem_servico");
                itemOrdemServico = new ItemOrdemServico(id, descricao, preco, idOrdemServico);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemOrdemServico;
    }

    public List<ItemOrdemServico> selectAllItensOrdemServico() {
        List<ItemOrdemServico> itensOrdemServico = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ALL_ITEM_ORDEM_SERVICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                Long preco = rs.getLong("preco");
                int idOrdemServico = rs.getInt("id_ordem_servico");
                itensOrdemServico.add(new ItemOrdemServico(id, descricao, preco, idOrdemServico));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itensOrdemServico;
    }

    public boolean deleteItemOrdemServico(int id) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(DELETE_ITEM_ORDEM_SERVICO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateItemOrdemServico(ItemOrdemServico itemOrdemServico) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(UPDATE_ITEM_ORDEM_SERVICO_SQL)) {
            statement.setString(1, itemOrdemServico.getDescricao());
            statement.setLong(2, itemOrdemServico.getPreco());
            statement.setInt(3, itemOrdemServico.getIdOrdemServico());
            statement.setInt(4, itemOrdemServico.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
