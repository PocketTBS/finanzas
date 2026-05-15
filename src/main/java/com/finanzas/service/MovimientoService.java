package com.finanzas.service;

import com.finanzas.interfaces.IMovimientoDAO;
import com.finanzas.interfaces.IMovimientoService;
import com.finanzas.model.movimiento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService implements IMovimientoService {

    private final IMovimientoDAO dao;

    public MovimientoService(IMovimientoDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<movimiento> listarTodos() {
        return dao.findAll();
    }

    @Override
    public movimiento buscarPorId(int id) {
        return dao.findById(id);
    }

    @Override
    public void crear(movimiento m) {
        dao.save(m);
    }

    @Override
    public void actualizar(int id, movimiento m) {
        dao.update(id, m);
    }

    @Override
    public void eliminar(int id) {
        dao.delete(id);
    }
}
