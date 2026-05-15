package com.finanzas;

import com.finanzas.interfaces.IMovimientoDAO;
import com.finanzas.model.movimiento;
import com.finanzas.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimientoServiceTest {

    private IMovimientoDAO dao;
    private MovimientoService service;

    @BeforeEach
    void setUp() {
        dao = Mockito.mock(IMovimientoDAO.class);
        service = new MovimientoService(dao);
    }

    @Test
    void listarTodos_debeRetornarLista() {
        movimiento m = new movimiento(1, "Mercado", 50000, "gasto", "comida", "2024-01-01");
        when(dao.findAll()).thenReturn(List.of(m));

        List<movimiento> resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Mercado", resultado.get(0).getDescripcion());
        verify(dao, times(1)).findAll();
    }

    @Test
    void buscarPorId_debeRetornarMovimiento() {
        movimiento m = new movimiento(1, "Mercado", 50000, "gasto", "comida", "2024-01-01");
        when(dao.findById(1)).thenReturn(m);

        movimiento resultado = service.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(dao, times(1)).findById(1);
    }

    @Test
    void buscarPorId_debeRetornarNullSiNoExiste() {
        when(dao.findById(99)).thenReturn(null);

        movimiento resultado = service.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    void crear_debeLlamarSave() {
        movimiento m = new movimiento(0, "Arriendo", 800000, "gasto", "vivienda", "2024-01-01");

        service.crear(m);

        verify(dao, times(1)).save(m);
    }

    @Test
    void eliminar_debeLlamarDelete() {
        service.eliminar(1);

        verify(dao, times(1)).delete(1);
    }
}