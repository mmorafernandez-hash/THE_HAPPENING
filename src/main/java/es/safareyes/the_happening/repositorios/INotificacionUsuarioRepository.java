package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.NotificacionUsuario;
import es.safareyes.the_happening.modelo.NotificacionUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificacionUsuarioRepository extends JpaRepository<NotificacionUsuario, NotificacionUsuarioId> {
}