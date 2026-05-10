package com.finanzas.dao;

import com.finanzas.model.movimiento;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovimientoDAO {

    private final String URL = "jdbc:mysql://localhost:3306/finanzas_db";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<movimiento> findAll() throws SQLException {
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
        }
        return lista;
    }

    public movimiento findById(int id) throws SQLException {
        String sql = "SELECT id, descripcion, monto, tipo, categoria, fecha FROM movimiento WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
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
        }
        return null;
    }

    public void save(movimiento m) throws SQLException {
        String sql = "INSERT INTO movimiento (descripcion, monto, tipo, categoria, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getDescripcion());
            ps.setDouble(2, m.getMonto());
            ps.setString(3, m.getTipo());
            ps.setString(4, m.getCategoria());
            ps.setString(5, m.getFecha());
            ps.executeUpdate();
        }
    }

    public void update(int id, movimiento m) throws SQLException {
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
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM movimiento WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}