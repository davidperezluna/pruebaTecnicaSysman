package com.tecnica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ciudad {

    @Id
    @GeneratedValue
    private Long id;
    private String codigo;
    private String nombre;
    @ManyToOne
    private Departamento departamento;
}
