package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioTO save (UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.save(usuario);
    }

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.findall();
    }

    public UsuarioTO findById(Long id) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.findById(id);
    }

    public boolean delete(Long id) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(id);
    }
}
