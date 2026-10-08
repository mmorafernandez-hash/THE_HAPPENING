package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Foto;
import es.safareyes.the_happening.repositorios.IFotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestFotoRepositorio {

    @Autowired
    private IFotoRepository fotoRepository;

    @Test
    void encuentraTodasLasFotos() {
        List<Foto> fotos = fotoRepository.findAll();
        assertNotNull(fotos);
    }
}
