package br.com.fiap.resource;

import br.com.fiap.bo.AvatarBO;
import br.com.fiap.to.AvatarTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/techleet/avatar")
public class AvatarResource {

    private AvatarBO avatarBO = new AvatarBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AvatarTO avatar) {
        try {
            AvatarTO resultado = avatarBO.save(avatar);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar avatar!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar avatar");
        }
    }

}
