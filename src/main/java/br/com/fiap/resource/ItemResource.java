package br.com.fiap.resource;

import br.com.fiap.bo.ItemBO;
import br.com.fiap.to.ItemTO;
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
@RequestMapping("/techleet/item")
public class ItemResource {

    private ItemBO itemBO = new ItemBO();

    @GetMapping
    public ResponseEntity<List<ItemTO>> findAll() {
        List<ItemTO> itens = itemBO.findAll();
        if (itens != null) {
            return ResponseEntity.status(HttpStatus.OK).body(itens);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(itens);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ItemTO item = itemBO.findById(id);
        if (item != null) {
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ItemTO item) {
        try {
            ItemTO resultado = itemBO.save(item);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar item!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody ItemTO item) {
        try {
            item.setId(id);
            ItemTO resultado = itemBO.update(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar item");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (itemBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }
    }
}
