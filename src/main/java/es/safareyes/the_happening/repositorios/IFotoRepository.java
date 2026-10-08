package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFotoRepository extends JpaRepository<Foto, Integer> {
    // IFotoRepository
    List<Foto> findByEventoId(Integer eventoId);
}