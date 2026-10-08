package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}