package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String promocion;

    @ManyToMany(mappedBy = "cursos")
    private Set<Usuario> usuarios = new HashSet<>();


    @ManyToMany
    @JoinTable(
        name = "curso_evento",
         joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private Set<Evento> eventos = new HashSet<>();


    @OneToMany(mappedBy = "curso")
    private Set<Foto> fotos = new HashSet<>();
}
