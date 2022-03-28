package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoDaoH2 implements IDao<Endereco> {

    private ConfigJDBC configJDBC;

    public EnderecoDaoH2() {
        this.configJDBC = new ConfigJDBC();
    }

    // CRUD

    // Salvar
    @Override
    public Endereco salvar(Endereco endereco) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = String.format("INSERT INTO enderecos " +
                "(rua, numero, bairro, cidade, estado) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                endereco.setId(keys.getInt(1));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    // Buscar por ID
    @Override
    public Optional<Endereco> buscar(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;

        String query = String.format(
                "SELECT id, rua, numero, bairro, cidade, estado " +
                "FROM enderecos WHERE id = '%s';", id);
        Endereco endereco = null;

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                endereco = criarObjetoEndereco(rs);
            }
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    // Buscar todos os registros
    @Override
    public List<Endereco> buscarTodos() {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = "SELECT * FROM enderecos;";
        List<Endereco> enderecos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                enderecos.add(criarObjetoEndereco(rs));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    // Atualizar
    @Override
    public Endereco atualizar(Endereco endereco) {
        Connection conn = configJDBC.connectToDB();
        String query = String.format(
                "UPDATE enderecos SET rua = '%s', numero = '%s', " +
                "bairro = '%s', cidade = '%s', estado = '%s' " +
                "WHERE id = '%s';",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getId());
        execute(conn, query);
        return endereco;
    }

    // Excluir
    @Override
    public void excluir(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;
        String query = String.format("DELETE FROM enderecos WHERE " +
                "id = '%s';", id);
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //

    private Endereco criarObjetoEndereco(ResultSet rs) throws SQLException {
        return new Endereco(
                rs.getInt("id"),
                rs.getString("rua"),
                rs.getString("numero"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado")
        );
    }

    private void execute(Connection conn, String query) {
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
