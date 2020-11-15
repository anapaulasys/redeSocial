package com.posjava.redesocial;

import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.javafaker.Faker;
import java.util.Locale;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void populaMuitosUsuarios() {

        int quantidade = 1000;

        Faker faker = new Faker(new Locale("pt-BR"));

        while (quantidade > 0) {

            try {
                Usuario usuario = new Usuario();
                usuario.setNome(faker.name().fullName());
                usuario.setEmail(faker.internet().emailAddress());
                usuario.setSenha(faker.internet().password());
                usuario.setBiografia(faker.gameOfThrones().quote());
                usuario.setFoto(faker.internet().avatar());
                Usuario retorno = usuarioService.save(usuario);
                Assertions.assertNotNull(retorno.getId());
                quantidade--;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Assertions.assertEquals(quantidade, 0);
    }
}
