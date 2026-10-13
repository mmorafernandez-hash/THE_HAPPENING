package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Inscripcion;
import es.safareyes.the_happening.repositorios.IInscripcionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestInscripcionRepositorio {


    @Autowired
    private IInscripcionRepository inscripcionRepository;



    @Test
    void encuentraTodasLasInscripciones() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        assertNotNull(inscripciones);
    }

    @Test
    void cuentaInscripcionesConfirmadas() {
        long total = inscripcionRepository.countByEventoIdAndEstado(1, "CONFIRMADA");
        assertTrue(total >= 0);
    }


    @Test // Q1 Derivados
    void derivadoUsuarioYEvento() {
        var resultado = inscripcionRepository.findByUsuarioIdAndEventoId(1L, 1);
        System.out.println(">>> Q1 inscripción usuario 1 / evento 1: "
                + resultado.map(i -> i.getId() + " | " + i.getEstado()).orElse("no existe"));
        assertNotNull(resultado);
    }

    @Test // Q1 Derivados
    void derivadoPrimeroEnEspera() {
        var resultado = inscripcionRepository.findFirstByEventoIdAndEstadoOrderByFechaAsc(1, "EN_ESPERA");
        System.out.println(">>> Q1 primero en espera del evento 1: "
                + resultado.map(i -> i.getId() + " | " + i.getFecha()).orElse("nadie en espera"));
        assertNotNull(resultado);
    }

    @Test // Q2 JPQL
    void jpqlInscritosPorTipo() {
        System.out.println(">>> Q2 inscritos del evento 1 por estado:");
        inscripcionRepository.contarInscritosPorTipo(1).forEach(f -> System.out.println("   " + Arrays.toString(f)));
    }

    @Test // Q6 Paginación
    void paginacionInscripciones() {
        Page<Inscripcion> pagina = inscripcionRepository.findByEventoId(1, PageRequest.of(0, 5));
        System.out.println(">>> Q6 inscripciones del evento 1, total: " + pagina.getTotalElements());
        pagina.getContent().forEach(i -> System.out.println("   " + i.getId() + " | " + i.getEstado()));
        assertNotNull(pagina);
    }
}