package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository <Categoria, Integer>{

}
