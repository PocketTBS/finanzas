package com.finanzas.dao;

import com.finanzas.model.movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<movimiento, Integer> {
}
