package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso, Integer> {
}