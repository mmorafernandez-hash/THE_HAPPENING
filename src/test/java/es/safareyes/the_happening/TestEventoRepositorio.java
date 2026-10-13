package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Evento;
import es.safareyes.the_happening.repositorios.IEventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestEventoRepositorio {

    @Autowired
    private IEventoRepository eventoRepository;

    private final LocalDateTime desde = LocalDateTime.of(2026, 10, 1, 0, 0);
    private final LocalDateTime hasta = LocalDateTime.of(2026, 11, 1, 0, 0);



    @Test
    void encuentraTodosLosEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        assertFalse(eventos.isEmpty(), "La lista de eventos no debería estar vacía");
    }

    @Test
    void calendarioDevuelveEventosDelMes() {
        List<Evento> eventos = eventoRepository.findByFechaInicioBetweenOrderByFechaInicio(
                LocalDateTime.of(2026, 10, 1, 0, 0),
                LocalDateTime.of(2026, 10, 31, 23, 59, 59));

        assertFalse(eventos.isEmpty(), "Debería encontrar los eventos de octubre");
    }

    @Test
    void encuentraEventosOrganizadosPorUsuario() {
        List<Evento> eventos = eventoRepository.findByOrganizadorId(1);
        assertFalse(eventos.isEmpty(), "El usuario 1 debería tener eventos asignados");
    }


    @Test // Q1 Derivados
    void derivadoEstadoYFechas() {
        List<Evento> eventos = eventoRepository.findByEstadoAndFechaInicioBetweenOrderByFechaInicioAsc("PUBLICADO", desde, hasta);
        System.out.println(">>> Q1 eventos PUBLICADOS en octubre:");
        eventos.forEach(e -> System.out.println("   " + e.getId() + " | " + e.getTitulo() + " | " + e.getFechaInicio()));
        assertNotNull(eventos);
    }

    @Test // Q2 JPQL
    void jpqlSolapamientoEnLugar() {
        List<Evento> eventos = eventoRepository.findSolapadosEnLugar(1, desde, hasta);
        System.out.println(">>> Q2 eventos solapados en el lugar 1:");
        eventos.forEach(e -> System.out.println("   " + e.getId() + " | " + e.getTitulo()));
        assertNotNull(eventos);
    }

    @Test // Q2 JPQL
    void jpqlVisiblesParaCurso() {
        List<Evento> eventos = eventoRepository.findVisiblesParaCurso(1);
        System.out.println(">>> Q2 eventos visibles para el curso 1:");
        eventos.forEach(e -> System.out.println("   " + e.getId() + " | " + e.getTitulo()));
        assertNotNull(eventos);
    }

    @Test // Q3 Nativa
    void nativaEventosPorDia() {
        System.out.println(">>> Q3 eventos por día (DATE):");
        eventoRepository.eventosPorDia(desde, hasta).forEach(f -> System.out.println("   " + Arrays.toString(f)));
        System.out.println(">>> Q3 eventos por día (DATE_TRUNC):");
        eventoRepository.eventosPorDiaTrunc(desde, hasta).forEach(f -> System.out.println("   " + Arrays.toString(f)));
    }

    @Test // Q3 Nativa
    void nativaParticipacion() {
        System.out.println(">>> Q3 participación por categoría:");
        eventoRepository.participacionNativaPorCategoria().forEach(f -> System.out.println("   " + Arrays.toString(f)));
        System.out.println(">>> Q3 participación por curso:");
        eventoRepository.participacionNativaPorCurso().forEach(f -> System.out.println("   " + Arrays.toString(f)));
    }

    @Test // Q4 Modificación (@DataJpaTest hace rollback al terminar)
    void modificacionFinalizaEventosTerminados() {
        int filas = eventoRepository.finalizarEventosTerminados(LocalDateTime.now());
        System.out.println(">>> Q4 eventos pasados a FINALIZADO: " + filas);
        assertTrue(filas >= 0);
    }

    @Test // Q6 Paginación
    void paginacionEventos() {
        Page<Evento> pagina = eventoRepository.findAll(PageRequest.of(0, 5));
        System.out.println(">>> Q6 página " + pagina.getNumber() + " de " + pagina.getTotalPages()
                + " | total eventos: " + pagina.getTotalElements());
        pagina.getContent().forEach(e -> System.out.println("   " + e.getId() + " | " + e.getTitulo()));
        assertEquals(5, pagina.getSize());
    }

    @Test // Q8 EntityGraph
    void entityGraphDetalle() {
        eventoRepository.findDetalleById(1).ifPresent(e -> {
            System.out.println(">>> Q8 evento " + e.getTitulo());
            System.out.println("   lugar: " + e.getLugar().getNombre());
            System.out.println("   categoría: " + e.getCategoria().getNombre());
            System.out.println("   cursos: " + e.getCursos().size());
        });
    }

    @Test // Q8 EntityGraph
    void entityGraphPublicacionesYFotos() {
        eventoRepository.findConPublicacionesYFotosById(1).ifPresent(e -> {
            System.out.println(">>> Q8 evento " + e.getTitulo());
            System.out.println("   publicaciones: " + e.getPublicaciones().size());
            System.out.println("   fotos: " + e.getFotos().size());
        });
    }
}