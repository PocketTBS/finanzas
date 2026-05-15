package com.finanzas.controller;

import com.finanzas.interfaces.IMovimientoService;
import com.finanzas.model.movimiento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos", description = "Operaciones para gestionar movimientos financieros")
public class MovimientoController {

    private final IMovimientoService service;

    public MovimientoController(IMovimientoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los movimientos")
    public ResponseEntity<List<movimiento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un movimiento por ID")
    public ResponseEntity<movimiento> buscarPorId(@PathVariable int id) {
        movimiento m = service.buscarPorId(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo movimiento")
    public ResponseEntity<String> crear(@RequestBody movimiento m) {
        service.crear(m);
        return ResponseEntity.ok("Movimiento creado exitosamente");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un movimiento existente")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody movimiento m) {
        service.actualizar(id, m);
        return ResponseEntity.ok("Movimiento actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un movimiento por ID")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.ok("Movimiento eliminado exitosamente");
    }
}