package br.com.fiap.resource;

import br.com.fiap.bo.MissaoBO;
import br.com.fiap.to.MissaoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techleet/missao")
public class MissaoResource {

    private MissaoBO missaoBO = new MissaoBO();

    @GetMapping
    public ResponseEntity<List<MissaoTO>> findAll() {
        List<MissaoTO> missoes = missaoBO.findAll();
        if (missoes != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missoes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        MissaoTO missao = missaoBO.findById(id);
        if (missao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid MissaoTO missao) {
        try {
            MissaoTO resultado = missaoBO.save(missao);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar missao!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(missao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar missao");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (missaoBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao não encontrado");
        }
    }
}
