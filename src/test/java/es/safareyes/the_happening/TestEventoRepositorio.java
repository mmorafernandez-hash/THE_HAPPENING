package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Evento;
import es.safareyes.the_happening.repositorios.IEventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestEventoRepositorio {

    @Autowired
    private IEventoRepository eventoRepository;

    @Test
    void encuentraTodosLosEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        assertNotNull(eventos);
    }
    @Test
    void calendarioDevuelveEventosDelMes() {
        List<Evento> eventos = eventoRepository.findByFechaInicioBetweenOrderByFechaInicio(
                LocalDateTime.of(2026, 9, 1, 0, 0),
                LocalDateTime.of(2026, 10, 31, 23, 59));
        assertFalse(eventos.isEmpty());
    }

    @Test
    void encuentraEventosOrganizadosPorUsuario() {
        List<Evento> eventos = eventoRepository.findByOrganizadorId(1);
        assertNotNull(eventos);
    }
}