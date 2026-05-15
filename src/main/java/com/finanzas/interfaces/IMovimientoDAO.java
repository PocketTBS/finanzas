package com.finanzas.interfaces;

import com.finanzas.model.movimiento;
import java.util.List;

public interface IMovimientoDAO {
    List<movimiento> findAll();
    movimiento findById(int id);
    void save(movimiento m);
    void update(int id, movimiento m);
    void delete(int id);
}