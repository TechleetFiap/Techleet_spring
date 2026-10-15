package br.com.fiap.bo;

import br.com.fiap.dao.ParceriaDAO;
import br.com.fiap.to.ParceriaTO;

import java.util.ArrayList;

public class ParceriaBO {

    private ParceriaDAO parceriaDAO;

    public ArrayList<ParceriaTO> findAll() {
        parceriaDAO = new ParceriaDAO();
        // aqui se implementa a regra de negocio
        return parceriaDAO.findAll();
    }

    public ParceriaTO findById(Long id) {
        parceriaDAO = new ParceriaDAO();
        // aqui se implementa a regra de negocio
        return parceriaDAO.findById(id);
    }


    public ParceriaTO save (ParceriaTO parceria) {
        parceriaDAO = new ParceriaDAO();
        // aqui se implementa a regra de negocio

        return parceriaDAO.save(parceria);
    }

    public ParceriaTO update(ParceriaTO parceria) {
        parceriaDAO = new ParceriaDAO();

        return parceriaDAO.update(parceria);
    }

    public boolean delete(Long id) {
        parceriaDAO = new ParceriaDAO();

        return parceriaDAO.delete(id);
    }
}
