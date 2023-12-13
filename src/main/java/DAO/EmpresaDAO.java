package DAO;

import model.Empresa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO extends ConexaoDB {

    private static final String INSERT_EMPRESA_SQL = "INSERT INTO empresa (nome_fantasia, cnpj, logo, slogan, id_endereco) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_EMPRESA_BY_ID = "SELECT id, nome_fantasia, cnpj, logo, slogan, id_endereco FROM empresa WHERE id = ?";
    private static final String SELECT_ALL_EMPRESA = "SELECT * FROM empresa;";
    private static final String DELETE_EMPRESA_SQL = "DELETE FROM empresa WHERE id = ?;";
    private static final String UPDATE_EMPRESA_SQL = "UPDATE empresa SET nome_fantasia = ?, cnpj = ?, logo = ?, slogan = ?, id_endereco = ? WHERE id = ?;";
    private static final String TOTAL_EMPRESA = "SELECT count(1) FROM empresa;";

    public Integer countEmpresa() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(TOTAL_EMPRESA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertEmpresa(Empresa empresa) {
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(INSERT_EMPRESA_SQL)) {
            preparedStatement.setString(1, empresa.getNomeFantasia());
            preparedStatement.setString(2, empresa.getCnpj());
            preparedStatement.setBytes(3, empresa.getLogo());
            preparedStatement.setString(4, empresa.getSlogan());
            preparedStatement.setInt(5, empresa.getIdEndereco());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Empresa selectEmpresa(int id) {
        Empresa empresa = null;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_EMPRESA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nomeFantasia = rs.getString("nome_fantasia");
                String cnpj = rs.getString("cnpj");
                byte[] logo = rs.getBytes("logo");
                String slogan = rs.getString("slogan");
                int idEndereco = rs.getInt("id_endereco");
                empresa = new Empresa(id, nomeFantasia, cnpj, logo, slogan, idEndereco);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empresa;
    }

    public List<Empresa> selectAllEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ALL_EMPRESA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeFantasia = rs.getString("nome_fantasia");
                String cnpj = rs.getString("cnpj");
                byte[] logo = rs.getBytes("logo");
                String slogan = rs.getString("slogan");
                int idEndereco = rs.getInt("id_endereco");
                empresas.add(new Empresa(id, nomeFantasia, cnpj, logo, slogan, idEndereco));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empresas;
    }

    public boolean deleteEmpresa(int id) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(DELETE_EMPRESA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmpresa(Empresa empresa) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(UPDATE_EMPRESA_SQL)) {
            statement.setString(1, empresa.getNomeFantasia());
            statement.setString(2, empresa.getCnpj());
            statement.setBytes(3, empresa.getLogo());
            statement.setString(4, empresa.getSlogan());
            statement.setInt(5, empresa.getIdEndereco());
            statement.setInt(6, empresa.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
