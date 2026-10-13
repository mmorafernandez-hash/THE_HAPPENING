package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Inscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IInscripcionRepository extends JpaRepository<Inscripcion, Integer> {


    List<Inscripcion> findByEventoId(Integer eventoId);
    long countByEventoIdAndEstado(Integer eventoId, String estado);
    List<Inscripcion> findByUsuarioId(Integer usuarioId);

    //Q1 Derivados
    Optional<Inscripcion> findByUsuarioIdAndEventoId(Long usuarioId, Integer eventoId);
    Optional<Inscripcion> findFirstByEventoIdAndEstadoOrderByFechaAsc(Integer eventoId, String estado);

    //Q2 JPQL
    @Query("""
        SELECT i.estado, COUNT(i)
        FROM Inscripcion i
        WHERE i.evento.id = :eventoId
        GROUP BY i.estado
        """)
    List<Object[]> contarInscritosPorTipo(@Param("eventoId") Integer eventoId);


    Page<Inscripcion> findByEventoId(Integer eventoId, Pageable pageable);


    @Query("""
        SELECT c.nombre, COUNT(i)
        FROM Inscripcion i JOIN i.evento e JOIN e.categoria c
        WHERE i.estado = 'CONFIRMADA'
          AND e.fechaInicio BETWEEN :desde AND :hasta
        GROUP BY c.nombre
        """)
    List<Object[]> participacionPorCategoria(@Param("desde") LocalDateTime desde,
                                             @Param("hasta") LocalDateTime hasta);

    @Query("""
        SELECT cu.nombre, COUNT(i)
        FROM Inscripcion i JOIN i.evento e JOIN e.cursos cu
        WHERE i.estado = 'CONFIRMADA'
          AND e.fechaInicio BETWEEN :desde AND :hasta
        GROUP BY cu.nombre
        """)
    List<Object[]> participacionPorCurso(@Param("desde") LocalDateTime desde,
                                         @Param("hasta") LocalDateTime hasta);
}