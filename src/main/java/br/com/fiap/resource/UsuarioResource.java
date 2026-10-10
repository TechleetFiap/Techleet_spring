package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techleet/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @PostMapping
    public ResponseEntity<?> save (@RequestBody @Valid UsuarioTO usuario) {
        try {
            UsuarioTO resultado = usuarioBO.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar usuario!");
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioTO>> findAll() {
        List<UsuarioTO> usuario = usuarioBO.findAll();
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }  else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuario);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        UsuarioTO usuario = usuarioBO.findById(id);
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (usuarioBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
    }
}
