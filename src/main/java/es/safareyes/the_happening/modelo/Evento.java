package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "fecha_limite")
    private LocalDateTime fechaLimite;

    private String estado;

    // ---------- RELACIONES ----------

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "lugar_id")
    private Lugar lugar;

    @ManyToOne
    @JoinColumn(name = "usuario_organizador_id")
    private Usuario organizador;

    // Tabla evento_requisito (dueño: Evento)
    @ManyToMany
    @JoinTable(
            name = "evento_requisito",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "requisito_id")
    )
    private Set<Requisito> requisitos = new HashSet<>();


    @ManyToMany(mappedBy = "eventos")
    private Set<Curso> cursos = new HashSet<>();

    @OneToMany(mappedBy = "evento")
    private Set<Inscripcion> inscripciones = new HashSet<>();

    @OneToMany(mappedBy = "evento")
    private Set<Publicacion> publicaciones = new HashSet<>();


    @OneToMany(mappedBy = "evento")
    private Set<Foto> fotos = new HashSet<>();

    @OneToMany(mappedBy = "evento")
    private Set<Notificacion> notificaciones = new HashSet<>();
}