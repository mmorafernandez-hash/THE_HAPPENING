package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventoRepository extends JpaRepository<Evento, Integer> {
    // 1. GET /eventos (filtros simples; sin filtro usa findAll())

    List<Evento> findByCategoriaId(Integer categoriaId);
    List<Evento> findByLugarId(Integer lugarId);
    List<Evento> findByEstado(String estado);
    List<Evento> findByTituloContainingIgnoreCase(String texto);

    // 2. GET /eventos/calendario (eventos de un mes)

    List<Evento> findByFechaInicioBetweenOrderByFechaInicio(LocalDateTime desde, LocalDateTime hasta);

    // 3. GET /eventos/{id}

    // 11. GET /usuarios/me/eventos?tipo=organizados

    List<Evento> findByOrganizadorId(Integer usuarioId);
}
