package com.finanzas.controller;

import com.finanzas.dao.DAOJPA;
import com.finanzas.model.movimiento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa/movimientos")
@Tag(name = "Movimientos JPA", description = "Operaciones usando JPA")
public class MovimientoJpaController {

    private final DAOJPA dao;

    public MovimientoJpaController(DAOJPA dao) {
        this.dao = dao;
    }

    @GetMapping
    @Operation(summary = "Listar todos los movimientos con JPA")
    public ResponseEntity<List<movimiento>> listarTodos() {
        return ResponseEntity.ok(dao.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un movimiento por ID con JPA")
    public ResponseEntity<movimiento> buscarPorId(@PathVariable int id) {
        movimiento m = dao.findById(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @PostMapping
    @Operation(summary = "Crear un movimiento con JPA")
    public ResponseEntity<String> crear(@RequestBody movimiento m) {
        dao.save(m);
        return ResponseEntity.ok("Movimiento creado con JPA");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un movimiento con JPA")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody movimiento m) {
        dao.update(id, m);
        return ResponseEntity.ok("Movimiento actualizado con JPA");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un movimiento con JPA")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        dao.delete(id);
        return ResponseEntity.ok("Movimiento eliminado con JPA");
    }
}