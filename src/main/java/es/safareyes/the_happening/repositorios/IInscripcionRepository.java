package es.safareyes.the_happening.repositorios;

import es.safareyes.the_happening.modelo.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IInscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    // 12. GET /eventos/{id}/inscripciones

    List<Inscripcion> findByEventoId(Integer eventoId);

    // 3. plazas libres = aforoMax - confirmadas

    long countByEventoIdAndEstado(Integer eventoId, String estado);

    // 11. GET /usuarios/me/eventos?tipo=inscritos

    List<Inscripcion> findByUsuarioId(Integer usuarioId);

    // 16. GET /informes/participacion (por categoría)
    @Query("""
        SELECT c.nombre, COUNT(i)
        FROM Inscripcion i JOIN i.evento e JOIN e.categoria c
        WHERE i.estado = 'CONFIRMADA'
          AND e.fechaInicio BETWEEN :desde AND :hasta
        GROUP BY c.nombre
        """)

    List<Object[]> participacionPorCategoria(@Param("desde") LocalDateTime desde,
                                             @Param("hasta") LocalDateTime hasta);

    // 16. GET /informes/participacion (por curso)

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