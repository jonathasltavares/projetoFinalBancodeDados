package DAO;

import model.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends ConexaoDB {

    private static final String INSERT_ENDERECO_SQL = "INSERT INTO endereco (rua, numero, bairro, cep, id_cidade) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ENDERECO_BY_ID = "SELECT id, rua, numero, bairro, cep, id_cidade FROM endereco WHERE id = ?";
    private static final String SELECT_ALL_ENDERECO = "SELECT * FROM endereco;";
    private static final String DELETE_ENDERECO_SQL = "DELETE FROM endereco WHERE id = ?;";
    private static final String UPDATE_ENDERECO_SQL = "UPDATE endereco SET rua = ?, numero = ?, bairro = ?, cep = ?, id_cidade = ? WHERE id = ?;";
    private static final String TOTAL_ENDERECO = "SELECT count(1) FROM endereco;";

    public Integer countEndereco() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(TOTAL_ENDERECO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertEndereco(Endereco endereco) {
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(INSERT_ENDERECO_SQL)) {
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getBairro());
            preparedStatement.setString(4, endereco.getCep());
            preparedStatement.setInt(5, endereco.getIdCidade());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Endereco selectEndereco(int id) {
        Endereco endereco = null;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ENDERECO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String bairro = rs.getString("bairro");
                String cep = rs.getString("cep");
                int idCidade = rs.getInt("id_cidade");
                endereco = new Endereco(id, rua, numero, bairro, cep, idCidade);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    public List<Endereco> selectAllEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ALL_ENDERECO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String bairro = rs.getString("bairro");
                String cep = rs.getString("cep");
                int idCidade = rs.getInt("id_cidade");
                enderecos.add(new Endereco(id, rua, numero, bairro, cep, idCidade));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    public boolean deleteEndereco(int id) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(DELETE_ENDERECO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEndereco(Endereco endereco) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(UPDATE_ENDERECO_SQL)) {
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getNumero());
            statement.setString(3, endereco.getBairro());
            statement.setString(4, endereco.getCep());
            statement.setInt(5, endereco.getIdCidade());
            statement.setInt(6, endereco.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
