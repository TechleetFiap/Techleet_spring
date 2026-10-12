package br.com.fiap.bo;

import br.com.fiap.dao.CodigoDAO;
import br.com.fiap.to.CodigoTO;

import java.util.ArrayList;

public class CodigoBO {

    private CodigoDAO codigoDAO;

    public ArrayList<CodigoTO> findAll() {
        codigoDAO = new CodigoDAO();
        // aqui se implementa a regra de negocio
        return codigoDAO.findAll();
    }

    public CodigoTO findById(Long id) {
        codigoDAO = new CodigoDAO();
        // aqui se implementa a regra de negocio
        return codigoDAO.findById(id);
    }


    public CodigoTO save (CodigoTO codigo) {
        codigoDAO = new CodigoDAO();

        return codigoDAO.save(codigo);
    }

    public boolean delete(Long id) {
        codigoDAO = new CodigoDAO();

        return codigoDAO.delete(id);
    }
}
