package com.tecnica.repository;

import com.tecnica.entity.Ciudad;
import com.tecnica.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findAllByTipoAndFechaCompra(String tipo, Date fechaCompra);
    List<Material> findAllByCiudad(Ciudad ciudad);
}
