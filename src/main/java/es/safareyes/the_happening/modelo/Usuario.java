package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String username;
    private String email;
    private String password;
    private String rol;

    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;

    // ---------- RELACIONES (descomentar al crear cada clase) ----------

    @OneToMany(mappedBy = "organizador")
    private Set<Evento> eventosOrganizados = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Inscripcion> inscripciones = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Publicacion> publicaciones = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Foto> fotos = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<NotificacionUsuario> notificaciones = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "curso_usuario",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos = new HashSet<>();
}
