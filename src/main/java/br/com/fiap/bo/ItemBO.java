package br.com.fiap.bo;

import br.com.fiap.dao.ItemDAO;
import br.com.fiap.to.ItemTO;

import java.util.ArrayList;
import java.util.Locale;

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

        if (itemTO == null || itemTO.getNome() == null
                || itemTO.getModelo() == null || itemTO.getTipo() == null) {
            throw new RuntimeException("Nome, modelo e tipo do item são obrigatórios.");
        }

        itemTO.setNome(itemTO.getNome().trim());
        itemTO.setModelo(itemTO.getModelo().trim().toUpperCase(Locale.ROOT));
        itemTO.setTipo(itemTO.getTipo().trim().toUpperCase(Locale.ROOT));

        if ("NORMAL".equals(itemTO.getTipo())
                && (itemTO.getValorPontos() == null || itemTO.getValorPontos() < 0)) {
            throw new RuntimeException("Item normal deve ter um preço em pontos maior ou igual a zero.");
        }

        ArrayList<ItemTO> itens = itemDAO.findAll();
        if (itens == null) {
            throw new RuntimeException("Não foi possível verificar os itens cadastrados.");
        }
        for (ItemTO cadastrado : itens) {
            if (cadastrado.getNome().trim().equalsIgnoreCase(itemTO.getNome())
                    && cadastrado.getModelo().trim().equalsIgnoreCase(itemTO.getModelo())) {
                throw new RuntimeException("Já existe um item com este nome e modelo.");
            }
        }

        return itemDAO.save(itemTO);
    }

    public ItemTO update(ItemTO item) {
        itemDAO = new ItemDAO();

        return itemDAO.update(item);
    }

    public boolean delete(Long id) {
        itemDAO = new ItemDAO();

        return itemDAO.delete(id);
    }
}
