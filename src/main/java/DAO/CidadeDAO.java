package DAO;


import model.Cidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO extends ConexaoDB {

    private static final String INSERT_CIDADE_SQL = "INSERT INTO cidade (descricao, codigo, id_uf) VALUES (?, ?, ?);";
    private static final String SELECT_CIDADE_BY_ID = "SELECT id, descricao, codigo, id_uf FROM cidade WHERE id = ?";
    private static final String SELECT_ALL_CIDADE = "SELECT * FROM cidade;";
    private static final String DELETE_CIDADE_SQL = "DELETE FROM cidade WHERE id = ?;";
    private static final String UPDATE_CIDADE_SQL = "UPDATE cidade SET descricao = ?, codigo = ?, id_uf = ? WHERE id = ?;";
    private static final String TOTAL_CIDADE = "SELECT count(1) FROM cidade;";

    public Integer countCidade() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(TOTAL_CIDADE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertCidade(Cidade cidade) {
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(INSERT_CIDADE_SQL)) {
            preparedStatement.setString(1, cidade.getDescricao());
            preparedStatement.setInt(2, cidade.getCodigo());
            preparedStatement.setInt(3, cidade.getIdUf());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Cidade selectCidade(int id) {
        Cidade cidade = null;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_CIDADE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                int codigo = rs.getInt("codigo");
                int idUf = rs.getInt("id_uf");
                cidade = new Cidade(id, descricao, codigo, idUf);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cidade;
    }

    public List<Cidade> selectAllCidades() {
        List<Cidade> cidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ALL_CIDADE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int codigo = rs.getInt("codigo");
                int idUf = rs.getInt("id_uf");
                cidades.add(new Cidade(id, descricao, codigo, idUf));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cidades;
    }

    public boolean deleteCidade(int id) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(DELETE_CIDADE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCidade(Cidade cidade) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(UPDATE_CIDADE_SQL)) {
            statement.setString(1, cidade.getDescricao());
            statement.setInt(2, cidade.getCodigo());
            statement.setInt(3, cidade.getIdUf());
            statement.setInt(4, cidade.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
