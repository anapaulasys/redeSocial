package com.posjava.redesocial;

import com.github.javafaker.Faker;
import com.posjava.redesocial.model.Comentario;
import com.posjava.redesocial.model.Postagem;
import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.service.ComentarioService;
import com.posjava.redesocial.service.PostagemService;
import com.posjava.redesocial.service.UsuarioService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class PostagemTest {

    @Autowired
    PostagemService postagemService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ComentarioService comentarioService;

    @Test
    public void testInsert() {
        Page<Usuario> usuarios = usuarioService.findAll(null, PageRequest.of(0, 1));
        if (usuarios.hasContent()) {
            Postagem postagem = new Postagem();
            postagem.setConteudo("Teste Inicial");
            postagem.setUsuario(usuarios.getContent().get(0));
            postagemService.save(postagem);
            Assertions.assertNotNull(postagem.getId());
            Assertions.assertNotNull(postagem.getCriadoEm());
        }
    }

    @Test
    public void insertMuitasPostagens() {
        Page<Usuario> usuarios = usuarioService.findAll(null, PageRequest.of(0, 999));
        List<Postagem> inseridas = Lists.newArrayList();
        for (Usuario usuario : usuarios.getContent()) {
            Postagem postagem = new Postagem();
            postagem.setConteudo(new Faker().harryPotter().quote());
            postagem.setUsuario(usuario);
            inseridas.add(postagemService.save(postagem));
        }
        Assertions.assertEquals(usuarios.getSize(), inseridas.size());
    }

    @Test
    public void comentaMuito() {
        Page<Usuario> usuarios = usuarioService.findAll(null, PageRequest.of(0, 1));
        Page<Postagem> postagens = postagemService.findAll(null, PageRequest.of(0, 1));
        Faker faker = new Faker();
        int qtd = 1000;
        while (qtd > 0) {
            Comentario comentario = new Comentario();
            comentario.setConteudo(faker.lorem().paragraph());
            comentario.setCriadoEm(new Date());
            comentario.setUsuario(usuarios.getContent().get(0));
            comentario.setPostagem(postagens.getContent().get(0));
            comentarioService.save(comentario);
            qtd --;
        }
        Assertions.assertEquals(qtd, 0);
    }
}
