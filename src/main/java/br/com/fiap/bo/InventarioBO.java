package br.com.fiap.bo;

import br.com.fiap.dao.InventarioDAO;
import br.com.fiap.to.InventarioTO;

public class InventarioBO {

    private InventarioDAO inventarioDAO;

    public InventarioTO save (InventarioTO inventarioTO) {
        inventarioDAO = new InventarioDAO();

        return inventarioDAO.save(inventarioTO);
    }
}
