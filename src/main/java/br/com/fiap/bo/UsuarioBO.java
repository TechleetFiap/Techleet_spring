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
        usuario.setPontos(100);

        ArrayList<UsuarioTO> usuarios = usuarioDAO.findAll();
        if (usuarios == null) {
            throw new RuntimeException("Não foi possível verificar o e-mail.");
        }
        for (UsuarioTO cadastrado : usuarios) {
            if (cadastrado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new RuntimeException("E-mail já cadastrado.");
            }
        }

        UsuarioTO resultado = usuarioDAO.save(usuario);
        if (resultado == null) {
            return null;
        }
        return usuarioDAO.findByEmail(usuario.getEmail());
    }

    public UsuarioTO login(UsuarioTO login) {
        if (login == null || login.getEmail() == null || login.getEmail().isBlank()
                || login.getSenha() == null || login.getSenha().isBlank()) {
            return null;
        }

        usuarioDAO = new UsuarioDAO();
        UsuarioTO usuario = usuarioDAO.findByEmail(login.getEmail());
        if (usuario == null || !usuario.getSenha().equals(login.getSenha())) {
            return null;
        }
        return usuario;
    }

    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("ID do usuário é obrigatório.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RuntimeException("E-mail é obrigatório.");
        }

        UsuarioTO atual = usuarioDAO.findById(usuario.getId());
        if (atual == null || atual.getId() == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        usuario.setEmail(usuario.getEmail().trim());
        ArrayList<UsuarioTO> usuarios = usuarioDAO.findAll();
        if (usuarios == null) {
            throw new RuntimeException("Não foi possível verificar o e-mail.");
        }
        for (UsuarioTO cadastrado : usuarios) {
            if (!cadastrado.getId().equals(usuario.getId())
                    && cadastrado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new RuntimeException("E-mail já cadastrado.");
            }
        }

        usuario.setPontos(atual.getPontos());
        return usuarioDAO.update(usuario);
    }

    public boolean delete(Long id) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.delete(id);
    }
}
