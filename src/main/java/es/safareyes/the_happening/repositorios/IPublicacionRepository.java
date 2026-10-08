package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPublicacionRepository extends JpaRepository<Publicacion, Integer> {
    // IPublicacionRepository  -> 15. GET /eventos/{id}/publicaciones
    List<Publicacion> findByEventoId(Integer eventoId);
}