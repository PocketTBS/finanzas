package com.finanzas.dao;

import com.finanzas.interfaces.IMovimientoDAO;
import com.finanzas.model.movimiento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOJPA implements IMovimientoDAO {

    private final MovimientoRepository repository;

    public DAOJPA(MovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<movimiento> findAll() {
        return repository.findAll();
    }

    @Override
    public movimiento findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(movimiento m) {
        repository.save(m);
    }

    @Override
    public void update(int id, movimiento m) {
        m.setId(id);
        repository.save(m);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}