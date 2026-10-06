package br.com.fiap.resource;

import br.com.fiap.bo.CodigoBO;
import br.com.fiap.to.CodigoTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techleet/codigo")
public class CodigoResource {

    private CodigoBO codigoBO;

    @PostMapping
    public ResponseEntity<?> save (@RequestBody CodigoTO codigo) {
        try {
            CodigoTO resultado = codigoBO.save(codigo);
            return ResponseEntity.status(HttpStatus.CREATED).body(codigo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar codigo");
        }
    }
}
