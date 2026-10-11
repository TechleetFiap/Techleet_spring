package br.com.fiap.resource;

import br.com.fiap.bo.CodigoBO;
import br.com.fiap.to.CodigoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techleet/codigo")
public class CodigoResource {

    private CodigoBO codigoBO = new CodigoBO();

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
}
