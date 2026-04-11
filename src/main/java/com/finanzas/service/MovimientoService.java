package com.finanzas.service;

import com.finanzas.dao.MovimientoDAO;
import com.finanzas.model.movimiento;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoDAO dao;

    public MovimientoService(MovimientoDAO dao) {
        this.dao = dao;
    }

    public List<movimiento> listarTodos() throws SQLException {
        return dao.findAll();
    }

    public movimiento buscarPorId(int id) throws SQLException {
        return dao.findById(id);
    }

    public void crear(movimiento m) throws SQLException {
        dao.save(m);
    }

    public void actualizar(int id, movimiento m) throws SQLException {
        dao.update(id, m);
    }

    public void eliminar(int id) throws SQLException {
        dao.delete(id);
    }
}
