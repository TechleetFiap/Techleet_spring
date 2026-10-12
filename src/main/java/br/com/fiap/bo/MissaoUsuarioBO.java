package br.com.fiap.bo;

import br.com.fiap.dao.MissaoUsuarioDAO;
import br.com.fiap.to.MissaoUsuarioTO;

import java.util.ArrayList;

public class MissaoUsuarioBO {

    private MissaoUsuarioDAO missaoUsuarioDAO;

    public ArrayList<MissaoUsuarioTO> findAll() {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        // aqui se implementa a regra de negocio
        return missaoUsuarioDAO.findAll();
    }

    public MissaoUsuarioTO findById(Long id) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        // aqui se implementa a regra de negocio
        return missaoUsuarioDAO.findById(id);
    }


    public MissaoUsuarioTO save (MissaoUsuarioTO missaoUsuario) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        // aqui se implementa a regra de negocio

        return missaoUsuarioDAO.save(missaoUsuario);
    }

    public boolean delete(Long id) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();

        return missaoUsuarioDAO.delete(id);
    }
}
