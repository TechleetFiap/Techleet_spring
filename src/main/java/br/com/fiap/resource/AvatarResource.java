package br.com.fiap.resource;

import br.com.fiap.bo.AvatarBO;
import br.com.fiap.to.AvatarTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/techleet/avatar")
public class AvatarResource {

    private AvatarBO avatarBO;

    @PostMapping
    public ResponseEntity<?> save (@RequestBody AvatarTO avatar) {
        try {
            AvatarTO resultado = avatarBO.save(avatar);
            return ResponseEntity.status(HttpStatus.CREATED).body(avatar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar avatar");
        }
    }

}
