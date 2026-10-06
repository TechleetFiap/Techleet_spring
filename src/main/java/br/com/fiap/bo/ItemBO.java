package br.com.fiap.bo;

import br.com.fiap.dao.ItemDAO;
import br.com.fiap.to.ItemTO;

public class ItemBO {

    private ItemDAO itemDAO;

    public ItemTO save(ItemTO itemTO) {
        itemDAO = new ItemDAO();

        return itemDAO.save(itemTO);
    }
}
