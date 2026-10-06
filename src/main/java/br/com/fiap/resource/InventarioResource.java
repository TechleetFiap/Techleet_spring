package br.com.fiap.resource;

import br.com.fiap.bo.InventarioBO;
import br.com.fiap.to.InventarioTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techleet/inventario")
public class InventarioResource {

    private InventarioBO inventarioBO;

    public ResponseEntity<?> save(@RequestBody InventarioTO inventario) {
        try {
            InventarioTO resultado = inventarioBO.save(inventario);
            return ResponseEntity.status(HttpStatus.CREATED).body(inventario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar");
        }
    }
}
