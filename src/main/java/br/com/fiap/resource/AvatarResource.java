package br.com.fiap.resource;

import br.com.fiap.bo.AvatarBO;
import br.com.fiap.to.AvatarTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techleet/avatar")
public class AvatarResource {

    private AvatarBO avatarBO = new AvatarBO();

    @GetMapping
    public ResponseEntity<List<AvatarTO>> findAll() {
        List<AvatarTO> avatares = avatarBO.findAll();
        if (avatares != null) {
            return ResponseEntity.status(HttpStatus.OK).body(avatares);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(avatares);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AvatarTO avatar = avatarBO.findById(id);
        if (avatar != null) {
            return ResponseEntity.status(HttpStatus.OK).body(avatar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avatar não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AvatarTO avatar) {
        try {
            AvatarTO resultado = avatarBO.save(avatar);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar avatar!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar avatar");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody AvatarTO avatar) {
        try {
            avatar.setId(id);
            AvatarTO resultado = avatarBO.update(avatar);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar avatar");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar avatar");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (avatarBO.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avatar não encontrado");
        }
    }
}
