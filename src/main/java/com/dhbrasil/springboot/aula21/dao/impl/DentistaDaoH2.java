package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Dentista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DentistaDaoH2 implements IDao<Dentista> {

    private ConfigJDBC configJDBC;

    public DentistaDaoH2() {
        this.configJDBC = new ConfigJDBC();
    }

    // CRUD

    // Salvar
    @Override
    public Dentista salvar(Dentista dentista) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = String.format("INSERT INTO dentistas " +
                "(nome, email, numMatricula, atendeConvenio) " +
                "VALUES ('%s', '%s', '%s', '%s')",
                dentista.getNome(),
                dentista.getEmail(),
                dentista.getNumMatricula(),
                dentista.getAtendeConvenio());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                dentista.setId(keys.getInt(1));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista;
    }

    // Buscar por ID
    @Override
    public Optional<Dentista> buscar(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;

        String query = String.format(
                "SELECT id, nome, email, numMatricula, atendeConvenio " +
                "FROM dentistas WHERE id = '%s';", id);
        Dentista dentista = null;

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                dentista = criarObjetoDentista(rs);
            }
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista != null ? Optional.of(dentista) : Optional.empty();
        // Utilizamos o método .of quando temos certeza que o objeto tem dados
        // O método .empty retorna como vazio
        // Estes métodos do Objeto Optional<T> servem para evitar o NullPointerException
    }

    // Buscar todos os registros
    @Override
    public List<Dentista> buscarTodos() {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = "SELECT * FROM dentistas;";
        List<Dentista> dentistas = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dentistas.add(criarObjetoDentista(rs));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentistas;
    }

    // Atualizar
    @Override
    public Dentista atualizar(Dentista dentista) {
        Connection conn = configJDBC.connectToDB();
        String query = String.format(
                "UPDATE dentistas SET nome = '%s', email = '%s', " +
                "numMatricula = '%s', atendeConvenio = '%s' " +
                "WHERE id = '%s';",
                dentista.getNome(),
                dentista.getEmail(),
                dentista.getNumMatricula(),
                dentista.getAtendeConvenio(),
                dentista.getId());
        execute(conn, query);
        return dentista;
    }

    // Excluir
    @Override
    public void excluir(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;
        String query = String.format("DELETE FROM dentistas WHERE " +
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

    private Dentista criarObjetoDentista(ResultSet rs) throws SQLException {
        return new Dentista(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getInt("numMatricula"),
                rs.getInt("atendeConvenio")
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
