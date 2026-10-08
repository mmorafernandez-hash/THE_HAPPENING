package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Notificacion;
import es.safareyes.the_happening.repositorios.INotificacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestNotificacionRepositorio {

    @Autowired
    private INotificacionRepository notificacionRepository;

    @Test
    void encuentraTodasLasNotificaciones() {
        List<Notificacion> notificaciones = notificacionRepository.findAll();
        assertNotNull(notificaciones);
    }
}
