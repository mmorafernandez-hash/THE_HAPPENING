package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Curso;
import es.safareyes.the_happening.modelo.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
public interface IEventoRepository extends JpaRepository<Evento, Integer>, JpaSpecificationExecutor<Evento> {

    List<Evento> findByCategoriaId(Integer categoriaId);
    List<Evento> findByLugarId(Integer lugarId);
    List<Evento> findByEstado(String estado);
    List<Evento> findByTituloContainingIgnoreCase(String texto);
    List<Evento> findByFechaInicioBetweenOrderByFechaInicio(LocalDateTime desde, LocalDateTime hasta);
    List<Evento> findByOrganizadorId(Integer usuarioId);

    //Q1 Derivados
    List<Evento> findByEstadoAndFechaInicioBetweenOrderByFechaInicioAsc(String estado, LocalDateTime desde, LocalDateTime hasta);

    //Q2 JPQL
    @Query("""
        SELECT e FROM Evento e
        WHERE e.lugar.id = :lugarId
          AND e.fechaInicio < :fin
          AND e.fechaFin > :inicio
        """)
    List<Evento> findSolapadosEnLugar(@Param("lugarId") Integer lugarId,
                                      @Param("inicio") LocalDateTime inicio,
                                      @Param("fin") LocalDateTime fin);

    @Query("""
        SELECT DISTINCT e FROM Evento e JOIN e.cursos c
        WHERE c.id = :cursoId AND e.estado = 'PUBLICADO'
        """)
    List<Evento> findVisiblesParaCurso(@Param("cursoId") Integer cursoId);

    @Query("""
        SELECT e FROM Evento e
        WHERE :curso MEMBER OF e.cursos AND e.estado = 'PUBLICADO'
        """)
    List<Evento> findVisiblesParaCursoMemberOf(@Param("curso") Curso curso);

    //Q3 Nativa
    @Query(value = """
        SELECT DATE(e.fecha_inicio) AS dia, COUNT(*) AS total
        FROM the_happening.evento e
        WHERE e.fecha_inicio >= :desde AND e.fecha_inicio < :hasta
        GROUP BY DATE(e.fecha_inicio)
        ORDER BY dia
        """, nativeQuery = true)
    List<Object[]> eventosPorDia(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

    @Query(value = """
        SELECT DATE_TRUNC('day', e.fecha_inicio) AS dia, COUNT(*) AS total
        FROM the_happening.evento e
        WHERE e.fecha_inicio >= :desde AND e.fecha_inicio < :hasta
        GROUP BY DATE_TRUNC('day', e.fecha_inicio)
        ORDER BY dia
        """, nativeQuery = true)
    List<Object[]> eventosPorDiaTrunc(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

    @Query(value = """
        SELECT c.nombre, COUNT(i.id)
        FROM the_happening.inscripcion i
        JOIN the_happening.evento e ON e.id = i.evento_id
        JOIN the_happening.categoria c ON c.id = e.categoria_id
        WHERE i.estado = 'CONFIRMADA'
        GROUP BY c.nombre
        """, nativeQuery = true)
    List<Object[]> participacionNativaPorCategoria();

    @Query(value = """
        SELECT cu.nombre, COUNT(i.id)
        FROM the_happening.inscripcion i
        JOIN the_happening.curso_evento ce ON ce.evento_id = i.evento_id
        JOIN the_happening.curso cu ON cu.id = ce.curso_id
        WHERE i.estado = 'CONFIRMADA'
        GROUP BY cu.nombre
        """, nativeQuery = true)
    List<Object[]> participacionNativaPorCurso();

    //Q4 Modificación
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Evento e SET e.estado = 'FINALIZADO' WHERE e.estado = 'PUBLICADO' AND e.fechaFin < :ahora")
    int finalizarEventosTerminados(@Param("ahora") LocalDateTime ahora);

    //Q6 Paginación
    Page<Evento> findByEstado(String estado, Pageable pageable);
    Page<Evento> findByCategoriaId(Integer categoriaId, Pageable pageable);

    //Q8
    @EntityGraph(attributePaths = {"lugar", "categoria", "cursos"})
    Optional<Evento> findDetalleById(Integer id);

    @EntityGraph(attributePaths = {"publicaciones", "fotos"})
    Optional<Evento> findConPublicacionesYFotosById(Integer id);
}