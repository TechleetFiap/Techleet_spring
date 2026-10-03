package br.com.fiap.bo;

import br.com.fiap.dao.AvatarDAO;
import br.com.fiap.to.AvatarTO;

public class AvatarBO {

    private AvatarDAO avatarDAO;

    public AvatarTO save (AvatarTO avatar) {
        avatarDAO = new AvatarDAO();

        return avatarDAO.save(avatar);
    }
}
