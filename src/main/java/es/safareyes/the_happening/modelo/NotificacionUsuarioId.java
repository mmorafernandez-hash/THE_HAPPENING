package es.safareyes.the_happening.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class NotificacionUsuarioId implements Serializable {

    @Column(name = "notificacion_id")
    private Integer notificacionId;

    @Column(name = "usuario_id")
    private Integer usuarioId;
}
