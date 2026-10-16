package br.com.fiap.resource;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.bo.InventarioBO;
import br.com.fiap.to.InventarioTO;
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
@RequestMapping("/techleet/inventario")
public class InventarioResource {

    private InventarioBO inventarioBO = new InventarioBO();

    @GetMapping
    public ResponseEntity<List<InventarioTO>> findAll() {
        List<InventarioTO> inventarios = inventarioBO.findAll();
        if (inventarios != null) {
            return ResponseEntity.status(HttpStatus.OK).body(inventarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(inventarios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        InventarioTO inventario = inventarioBO.findById(id);
        if (inventario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(inventario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventario não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid InventarioTO inventario) {
        try {
            InventarioTO resultado = inventarioBO.save(inventario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar inventario!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody InventarioTO inventario) {
        try {
            inventario.setId(id);
            InventarioTO resultado = inventarioBO.update(inventario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar inventario");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar inventario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            if (inventarioBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventario não encontrado");
            }
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
