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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestEventoRepositorio {

    @Autowired
    private IEventoRepository eventoRepository;

    @Test
    void encuentraTodosLosEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        assertFalse(eventos.isEmpty(), "La lista de eventos no debería estar vacía");
    }

    @Test
    void calendarioDevuelveEventosDelMes() {
        // Busca los eventos de octubre de 2026 (IDs 1 y 2 en tu DBeaver)
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
}