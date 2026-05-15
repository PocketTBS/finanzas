package com.finanzas.interfaces;

import com.finanzas.model.movimiento;
import java.util.List;

public interface IMovimientoService {
    List<movimiento> listarTodos();
    movimiento buscarPorId(int id);
    void crear(movimiento m);
    void actualizar(int id, movimiento m);
    void eliminar(int id);
}
