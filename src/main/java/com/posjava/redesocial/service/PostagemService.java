package com.posjava.redesocial.service;

import com.google.common.base.Strings;
import com.posjava.redesocial.model.Postagem;
import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.repository.PostagemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;

@Service
public class PostagemService extends AbstractService<Postagem> {

    @Autowired
    private PostagemJpaRepository postagemJpaRepository;

    @Override
    public JpaRepository<Postagem, Long> getRepository() {
        return postagemJpaRepository;
    }

    public Postagem save(Postagem postagem) {
        postagem.setCriadoEm(new Date());
        return super.save(postagem);
    }

    public Page<Postagem> findAll(String nome, Pageable page) {
        if (Strings.isNullOrEmpty(nome)) {
            return postagemJpaRepository.findAllByOrderByCriadoEmDesc(page);
        }
        return postagemJpaRepository.findAllByConteudoLike(nome, page);
    }


}
