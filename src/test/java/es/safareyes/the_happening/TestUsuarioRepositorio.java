package es.safareyes.the_happening;

import es.safareyes.the_happening.modelo.Usuario;
import es.safareyes.the_happening.repositorios.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestUsuarioRepositorio {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Test
    void encuentraTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }
}