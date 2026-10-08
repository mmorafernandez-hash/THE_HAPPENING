package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequisitoRepository extends JpaRepository<Requisito, Integer> {
}
