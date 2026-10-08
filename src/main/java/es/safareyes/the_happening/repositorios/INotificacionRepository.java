package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
}