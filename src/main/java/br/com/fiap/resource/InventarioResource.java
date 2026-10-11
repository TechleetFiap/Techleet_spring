package br.com.fiap.resource;

import br.com.fiap.bo.InventarioBO;
import br.com.fiap.to.InventarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techleet/inventario")
public class InventarioResource {

    private InventarioBO inventarioBO = new InventarioBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid InventarioTO inventario) {
        try {
            InventarioTO resultado = inventarioBO.save(inventario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar inventario!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar");
        }
    }
}
