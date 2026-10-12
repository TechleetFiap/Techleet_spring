package br.com.fiap.bo;

import br.com.fiap.dao.MissaoDAO;
import br.com.fiap.to.MissaoTO;

import java.util.ArrayList;

public class MissaoBO {

    private MissaoDAO missaoDAO;

    public ArrayList<MissaoTO> findAll() {
        missaoDAO = new MissaoDAO();
        // aqui se implementa a regra de negocio
        return missaoDAO.findAll();
    }

    public MissaoTO findById(Long id) {
        missaoDAO = new MissaoDAO();
        // aqui se implementa a regra de negocio
        return missaoDAO.findById(id);
    }


    public MissaoTO save (MissaoTO missao) {
        missaoDAO = new MissaoDAO();
        // aqui se implementa a regra de negocio

        return missaoDAO.save(missao);
    }

    public boolean delete(Long id) {
        missaoDAO = new MissaoDAO();

        return missaoDAO.delete(id);
    }
}
