package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notificacion_usuario")
public class NotificacionUsuario {

    @EmbeddedId
    private NotificacionUsuarioId id = new NotificacionUsuarioId();

    @ManyToOne
    @MapsId("notificacionId")
    @JoinColumn(name = "notificacion_id")
    private Notificacion notificacion;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Boolean leido;

    @Column(name = "fecha_lectura")
    private LocalDateTime fechaLectura;
}
