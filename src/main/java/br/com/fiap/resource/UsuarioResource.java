package br.com.fiap.resource;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.exception.PersistenciaException;
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

    @GetMapping
    public ResponseEntity<List<UsuarioTO>> findAll() {
        List<UsuarioTO> usuarios = usuarioBO.findAll();
        if (usuarios != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        UsuarioTO usuario = usuarioBO.findById(id);
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioTO usuario) {
        try {
            UsuarioTO resultado = usuarioBO.save(usuario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar usuario!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar usuario");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioTO login) {
        try {
            UsuarioTO usuario = usuarioBO.login(login);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail ou senha inválidos.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (PersistenciaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar login.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody UsuarioTO usuario) {
        try {
            usuario.setId(id);
            UsuarioTO resultado = usuarioBO.update(usuario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuario");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar usuario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (usuarioBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
    }
}
