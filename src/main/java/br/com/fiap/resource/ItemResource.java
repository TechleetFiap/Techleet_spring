package br.com.fiap.resource;

import br.com.fiap.bo.ItemBO;
import br.com.fiap.to.ItemTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techleet/item")
public class ItemResource {

    private ItemBO itemBO;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ItemTO item) {
        try {
            ItemTO resultado = itemBO.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar");
        }
    }
}
