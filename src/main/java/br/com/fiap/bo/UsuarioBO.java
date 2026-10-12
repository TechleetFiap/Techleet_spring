package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        // aqui se implementa a regra de negocio
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Long id) {
        usuarioDAO = new UsuarioDAO();
        // aqui se implementa a regra de negocio
        return usuarioDAO.findById(id);
    }


    public UsuarioTO save (UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        usuario.setPontos(0);

        ArrayList<UsuarioTO> usuarios = usuarioDAO.findAll();
        if (usuarios == null) {
            throw new RuntimeException("Não foi possível verificar o e-mail.");
        }
        for (UsuarioTO cadastrado : usuarios) {
            if (cadastrado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new RuntimeException("E-mail já cadastrado.");
            }
        }

        return usuarioDAO.save(usuario);
    }

    public boolean delete(Long id) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.delete(id);
    }
}
