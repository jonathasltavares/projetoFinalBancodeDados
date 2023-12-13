package DAO;

import model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConexaoDB {

    private static final String INSERT_CLIENTE_SQL = "INSERT INTO cliente (nome, dt_nascimento, cpf, email, id_endereco) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_CLIENTE_BY_ID = "SELECT id, nome, dt_nascimento, cpf, email, id_endereco FROM cliente WHERE id = ?";
    private static final String SELECT_ALL_CLIENTE = "SELECT * FROM cliente;";
    private static final String DELETE_CLIENTE_SQL = "DELETE FROM cliente WHERE id = ?;";
    private static final String UPDATE_CLIENTE_SQL = "UPDATE cliente SET nome = ?, dt_nascimento = ?, cpf = ?, email = ?, id_endereco = ? WHERE id = ?;";
    private static final String TOTAL_CLIENTE = "SELECT count(1) FROM cliente;";

    public Integer countCliente() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(TOTAL_CLIENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertCliente(Cliente cliente) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CLIENTE_SQL)) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setDate(2, cliente.getDtNascimento());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setInt(5, cliente.getIdEndereco());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Cliente selectCliente(int id) {
        Cliente cliente = null;
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_CLIENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                java.sql.Date dtNascimento = rs.getDate("dt_nascimento");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                int idEndereco = rs.getInt("id_endereco");
                cliente = new Cliente(id, nome, dtNascimento, cpf, email, idEndereco);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public List<Cliente> selectAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConexaoDB.prepararSQL(SELECT_ALL_CLIENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                java.sql.Date dtNascimento = rs.getDate("dt_nascimento");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                int idEndereco = rs.getInt("id_endereco");
                clientes.add(new Cliente(id, nome, dtNascimento, cpf, email, idEndereco));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public boolean deleteCliente(int id) {
        try (PreparedStatement statement = ConexaoDB.prepararSQL(DELETE_CLIENTE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCliente(Cliente cliente) {
        try (PreparedStatement statement = prepararSQL(UPDATE_CLIENTE_SQL)) {
            statement.setString(1, cliente.getNome());
            statement.setDate(2, cliente.getDtNascimento());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getEmail());
            statement.setInt(5, cliente.getIdEndereco());
            statement.setInt(6, cliente.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

