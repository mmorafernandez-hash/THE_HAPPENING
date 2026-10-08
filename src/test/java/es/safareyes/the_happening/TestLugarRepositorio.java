package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Lugar;
import es.safareyes.the_happening.repositorios.ILugarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestLugarRepositorio {

    @Autowired
    private ILugarRepository lugarRepository;

    @Test
    void encuentraTodosLosLugares() {
        List<Lugar> lugares = lugarRepository.findAll();
        assertNotNull(lugares);
    }

    private void assertNotNull(List<Lugar> lugares) {
    }
}