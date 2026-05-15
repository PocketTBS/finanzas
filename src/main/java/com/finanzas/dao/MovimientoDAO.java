package com.finanzas.dao;

import com.finanzas.interfaces.IMovimientoDAO;
import com.finanzas.model.movimiento;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Primary
@Repository
public class MovimientoDAO implements IMovimientoDAO {

    private final String URL = "jdbc:mysql://localhost:3306/finanzas_db";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public List<movimiento> findAll() {
        List<movimiento> lista = new ArrayList<>();
        String sql = "SELECT id, descripcion, monto, tipo, categoria, fecha FROM movimiento";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new movimiento(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getDouble("monto"),
                        rs.getString("tipo"),
                        rs.getString("categoria"),
                        rs.getString("fecha")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public movimiento findById(int id) {
        String sql = "SELECT id, descripcion, monto, tipo, categoria, fecha FROM movimiento WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new movimiento(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getDouble("monto"),
                            rs.getString("tipo"),
                            rs.getString("categoria"),
                            rs.getString("fecha")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(movimiento m) {
        String sql = "INSERT INTO movimiento (descripcion, monto, tipo, categoria, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getDescripcion());
            ps.setDouble(2, m.getMonto());
            ps.setString(3, m.getTipo());
            ps.setString(4, m.getCategoria());
            ps.setString(5, m.getFecha());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, movimiento m) {
        String sql = "UPDATE movimiento SET descripcion=?, monto=?, tipo=?, categoria=?, fecha=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getDescripcion());
            ps.setDouble(2, m.getMonto());
            ps.setString(3, m.getTipo());
            ps.setString(4, m.getCategoria());
            ps.setString(5, m.getFecha());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM movimiento WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}