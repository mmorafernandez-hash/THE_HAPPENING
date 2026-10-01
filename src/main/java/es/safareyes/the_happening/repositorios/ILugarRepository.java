package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILugarRepository extends JpaRepository<Lugar, Integer> {

}
