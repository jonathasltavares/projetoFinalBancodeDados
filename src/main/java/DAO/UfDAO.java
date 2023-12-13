package DAO;

import model.Uf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UfDAO extends ConexaoDB {

    private static final String INSERT_UF_SQL = "INSERT INTO uf (descricao, codigo) VALUES (?, ?);";
    private static final String SELECT_UF_BY_ID = "SELECT id, descricao, codigo FROM uf WHERE id = ?";
    private static final String SELECT_ALL_UF = "SELECT * FROM uf;";
    private static final String DELETE_UF_SQL = "DELETE FROM uf WHERE id = ?;";
    private static final String UPDATE_UF_SQL = "UPDATE uf SET descricao = ?, codigo = ? WHERE id = ?;";
    private static final String TOTAL_UF = "SELECT count(1) FROM uf;";

    public Integer countUf() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL_UF)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertUf(Uf uf) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_UF_SQL)) {
            preparedStatement.setString(1, uf.getDescricao());
            preparedStatement.setInt(2, uf.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Uf selectUf(int id) {
        Uf uf = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_UF_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                int codigo = rs.getInt("codigo");
                uf = new Uf(id, descricao, codigo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return uf;
    }

    public List<Uf> selectAllUfs() {
        List<Uf> ufs = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_UF)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int codigo = rs.getInt("codigo");
                ufs.add(new Uf(id, descricao, codigo));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ufs;
    }

    public boolean deleteUf(int id) {
        try (PreparedStatement statement = prepararSQL(DELETE_UF_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUf(Uf uf) {
        try (Connection connection = conexaoDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UF_SQL)) {
            preparedStatement.setString(1, uf.getDescricao());
            preparedStatement.setInt(2, uf.getCodigo());
            preparedStatement.setInt(3, uf.getId());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
