package com.tecnica.repository;

import com.tecnica.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRespository extends JpaRepository<Departamento, Long> {
}
