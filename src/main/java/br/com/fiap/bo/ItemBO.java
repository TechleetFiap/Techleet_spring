package br.com.fiap.bo;

import br.com.fiap.dao.ItemDAO;
import br.com.fiap.to.ItemTO;

import java.util.ArrayList;

public class ItemBO {

    private ItemDAO itemDAO;

    public ArrayList<ItemTO> findAll() {
        itemDAO = new ItemDAO();
        // aqui se implementa a regra de negocio
        return itemDAO.findAll();
    }

    public ItemTO findById(Long id) {
        itemDAO = new ItemDAO();
        // aqui se implementa a regra de negocio
        return itemDAO.findById(id);
    }


    public ItemTO save(ItemTO itemTO) {
        itemDAO = new ItemDAO();

        return itemDAO.save(itemTO);
    }

    public boolean delete(Long id) {
        itemDAO = new ItemDAO();

        return itemDAO.delete(id);
    }
}
