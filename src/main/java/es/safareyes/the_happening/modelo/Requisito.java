package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "requisitos")
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;

    // ---------- RELACIONES (descomentar al crear Evento) ----------

    // Lado inverso de Evento.requisitos (tabla evento_requisito).
    @ManyToMany(mappedBy = "requisitos")
    private Set<Evento> eventos = new HashSet<>();
}