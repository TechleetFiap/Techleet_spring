package br.com.fiap.resource;

import br.com.fiap.bo.MissaoUsuarioBO;
import br.com.fiap.to.MissaoUsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techleet/missaoUsuario")
public class MissaoUsuarioResource {

    private MissaoUsuarioBO missaoUsuarioBO = new MissaoUsuarioBO();

    @GetMapping
    public ResponseEntity<List<MissaoUsuarioTO>> findAll() {
        List<MissaoUsuarioTO> missoesUsuario = missaoUsuarioBO.findAll();
        if (missoesUsuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missoesUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missoesUsuario);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        MissaoUsuarioTO missaoUsuario = missaoUsuarioBO.findById(id);
        if (missaoUsuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missaoUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MissaoUsuario não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid MissaoUsuarioTO missaoUsuario) {
        try {
            MissaoUsuarioTO resultado = missaoUsuarioBO.save(missaoUsuario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar missaoUsuario!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(missaoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar missaoUsuario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (missaoUsuarioBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MissaoUsuario não encontrado");
        }
    }
}
