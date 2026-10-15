package br.com.fiap.bo;

import br.com.fiap.dao.AvatarDAO;
import br.com.fiap.to.AvatarTO;

import java.util.ArrayList;

public class AvatarBO {

    private AvatarDAO avatarDAO;

    public ArrayList<AvatarTO> findAll() {
        avatarDAO = new AvatarDAO();
        // aqui se implementa a regra de negocio
        return avatarDAO.findAll();
    }

    public AvatarTO findById(Long id) {
        avatarDAO = new AvatarDAO();
        // aqui se implementa a regra de negocio
        return avatarDAO.findById(id);
    }


    public AvatarTO save (AvatarTO avatar) {
        avatarDAO = new AvatarDAO();

        return avatarDAO.save(avatar);
    }

    public AvatarTO update(AvatarTO avatar) {
        avatarDAO = new AvatarDAO();

        return avatarDAO.update(avatar);
    }

    public boolean delete(Long id) {
        avatarDAO = new AvatarDAO();

        return avatarDAO.delete(id);
    }
}
