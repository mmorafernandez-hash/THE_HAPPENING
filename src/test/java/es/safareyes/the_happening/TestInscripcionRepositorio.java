package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Inscripcion;
import es.safareyes.the_happening.repositorios.IInscripcionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}