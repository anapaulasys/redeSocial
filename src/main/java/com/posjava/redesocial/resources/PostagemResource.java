package com.posjava.redesocial.resources;

import com.posjava.redesocial.model.Postagem;
import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.service.AbstractService;
import com.posjava.redesocial.service.PostagemService;
import com.posjava.redesocial.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostagemResource extends AbstractResource<Postagem> {

    @Autowired
    private PostagemService postagemService;

    @Override
    public AbstractService<Postagem> getService() {
        return postagemService;
    }

    @PostMapping("/postagem")
    public ResponseEntity<Postagem> create(@RequestBody @Valid Postagem postagem) {
        return super.create(postagem);
    }

    @GetMapping("/postagem/{id}")
    public ResponseEntity<Postagem> get(@PathVariable Long id) {
        return super.get(id);
    }

    @DeleteMapping("/postagem/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @GetMapping("/postagem")
    public ResponseEntity<List<Postagem>> getAll(@RequestParam(value = "filtro", required = false) String filtro,
                                                 @RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit) {
        return super.getAll(filtro, page, limit);
    }

}
