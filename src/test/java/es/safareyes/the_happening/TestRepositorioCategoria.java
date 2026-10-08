package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Categoria;
import es.safareyes.the_happening.repositorios.ICategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestRepositorioCategoria {

    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private ICategoriaRepository Categoria;

    @Test
    void encuentraTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(Categoria);
    }
}