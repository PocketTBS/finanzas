package com.finanzas.controller;

import com.finanzas.model.movimiento;
import com.finanzas.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController(MovimientoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<movimiento>> listarTodos() throws SQLException {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<movimiento> buscarPorId(@PathVariable int id) throws SQLException {
        movimiento m = service.buscarPorId(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody movimiento m) throws SQLException {
        service.crear(m);
        return ResponseEntity.ok("Movimiento creado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody movimiento m) throws SQLException {
        service.actualizar(id, m);
        return ResponseEntity.ok("Movimiento actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) throws SQLException {
        service.eliminar(id);
        return ResponseEntity.ok("Movimiento eliminado exitosamente");
    }
}