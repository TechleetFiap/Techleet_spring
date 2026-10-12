package br.com.fiap.bo;

import br.com.fiap.dao.InventarioDAO;
import br.com.fiap.to.InventarioTO;

import java.util.ArrayList;

public class InventarioBO {

    private InventarioDAO inventarioDAO;

    public ArrayList<InventarioTO> findAll() {
        inventarioDAO = new InventarioDAO();
        // aqui se implementa a regra de negocio
        return inventarioDAO.findAll();
    }

    public InventarioTO findById(Long id) {
        inventarioDAO = new InventarioDAO();
        // aqui se implementa a regra de negocio
        return inventarioDAO.findById(id);
    }


    public InventarioTO save (InventarioTO inventarioTO) {
        inventarioDAO = new InventarioDAO();

        return inventarioDAO.save(inventarioTO);
    }

    public boolean delete(Long id) {
        inventarioDAO = new InventarioDAO();

        return inventarioDAO.delete(id);
    }
}
