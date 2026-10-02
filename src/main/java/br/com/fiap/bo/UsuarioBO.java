package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioTO save (UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.save(usuario);
    }
}
