package br.com.fiap.resource;

import br.com.fiap.bo.ParceriaBO;
import br.com.fiap.to.ParceriaTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techleet/parceria")
public class ParceriaResource {

    private ParceriaBO parceriaBO = new ParceriaBO();

    @GetMapping
    public ResponseEntity<List<ParceriaTO>> findAll() {
        List<ParceriaTO> parcerias = parceriaBO.findAll();
        if (parcerias != null) {
            return ResponseEntity.status(HttpStatus.OK).body(parcerias);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(parcerias);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ParceriaTO parceria = parceriaBO.findById(id);
        if (parceria != null) {
            return ResponseEntity.status(HttpStatus.OK).body(parceria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parceria não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ParceriaTO parceria) {
        try {
            ParceriaTO resultado = parceriaBO.save(parceria);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar parceria!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(parceria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar parceria");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody ParceriaTO parceria) {
        try {
            parceria.setId(id);
            ParceriaTO resultado = parceriaBO.update(parceria);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar parceria");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (parceriaBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parceria não encontrado");
        }
    }
}
