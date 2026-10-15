package br.com.fiap.resource;

import br.com.fiap.bo.CodigoBO;
import br.com.fiap.to.CodigoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/techleet/codigo")
public class CodigoResource {

    private CodigoBO codigoBO = new CodigoBO();

    @GetMapping
    public ResponseEntity<List<CodigoTO>> findAll() {
        List<CodigoTO> codigos = codigoBO.findAll();
        if (codigos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(codigos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(codigos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CodigoTO codigo = codigoBO.findById(id);
        if (codigo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(codigo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Codigo não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CodigoTO codigo) {
        try {
            CodigoTO resultado = codigoBO.save(codigo);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar codigo!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar codigo");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody CodigoTO codigo) {
        try {
            codigo.setId(id);
            CodigoTO resultado = codigoBO.update(codigo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar codigo");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (codigoBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Codigo não encontrado");
        }
    }
}
