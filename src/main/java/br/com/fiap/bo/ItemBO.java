package br.com.fiap.bo;

import br.com.fiap.exception.PersistenciaException;
import br.com.fiap.exception.RegistroDuplicadoException;
import br.com.fiap.exception.RegistroNaoEncontradoException;
import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.dao.AvatarDAO;
import br.com.fiap.dao.ItemDAO;
import br.com.fiap.to.AvatarTO;
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
            throw new RegraNegocioException("Nome, modelo e tipo do item são obrigatórios.");
        }

        itemTO.setNome(itemTO.getNome().trim());
        itemTO.setModelo(itemTO.getModelo().trim().toUpperCase(Locale.ROOT));
        itemTO.setTipo(itemTO.getTipo().trim().toUpperCase(Locale.ROOT));

        if (itemTO.getValorPontos() == null || itemTO.getValorPontos() < 0) {
            throw new RegraNegocioException("Todo item deve ter um preço em pontos maior ou igual a zero.");
        }

        ArrayList<ItemTO> itens = itemDAO.findAll();
        if (itens == null) {
            throw new PersistenciaException("Não foi possível verificar os itens cadastrados.");
        }
        for (ItemTO cadastrado : itens) {
            if (cadastrado.getNome().trim().equalsIgnoreCase(itemTO.getNome())
                    && cadastrado.getModelo().trim().equalsIgnoreCase(itemTO.getModelo())) {
                throw new RegistroDuplicadoException("Já existe um item com este nome e modelo.");
            }
        }

        return itemDAO.save(itemTO);
    }

    public ItemTO update(ItemTO item) {
        itemDAO = new ItemDAO();

        if (item == null || item.getId() == null) {
            throw new RegraNegocioException("ID do item é obrigatório.");
        }
        if (item.getNome() == null || item.getNome().isBlank()
                || item.getModelo() == null || item.getModelo().isBlank()
                || item.getTipo() == null || item.getTipo().isBlank()) {
            throw new RegraNegocioException("Nome, modelo e tipo do item são obrigatórios.");
        }
        ItemTO atual = itemDAO.findById(item.getId());
        if (atual == null || atual.getId() == null) {
            throw new RegistroNaoEncontradoException("Item não encontrado.");
        }

        item.setNome(item.getNome().trim());
        item.setModelo(item.getModelo().trim().toUpperCase(Locale.ROOT));
        item.setTipo(item.getTipo().trim().toUpperCase(Locale.ROOT));

        if (item.getValorPontos() != null && item.getValorPontos() < 0) {
            throw new RegraNegocioException("O preço do item não pode ser negativo.");
        }
        if (item.getValorPontos() == null) {
            throw new RegraNegocioException("Todo item deve ter um preço em pontos maior ou igual a zero.");
        }

        if (!atual.getModelo().equalsIgnoreCase(item.getModelo())) {
            AvatarDAO avatarDAO = new AvatarDAO();
            ArrayList<AvatarTO> avatares = avatarDAO.findAll();
            if (avatares == null) {
                throw new PersistenciaException("Não foi possível consultar os avatares.");
            }
            for (AvatarTO avatar : avatares) {
                if (item.getId().equals(avatar.getIdCabelo())
                        || item.getId().equals(avatar.getIdRoupaCimaInt())
                        || item.getId().equals(avatar.getIdRoupaCimaExt())
                        || item.getId().equals(avatar.getIdRoupaBaixo())
                        || item.getId().equals(avatar.getIdCalcado())
                        || item.getId().equals(avatar.getIdAcessorio())) {
                    throw new RegraNegocioException("Não é permitido trocar o modelo de um item equipado.");
                }
            }
        }

        ArrayList<ItemTO> itens = itemDAO.findAll();
        if (itens == null) {
            throw new PersistenciaException("Não foi possível verificar os itens cadastrados.");
        }
        for (ItemTO cadastrado : itens) {
            if (!cadastrado.getId().equals(item.getId())
                    && cadastrado.getNome().trim().equalsIgnoreCase(item.getNome())
                    && cadastrado.getModelo().trim().equalsIgnoreCase(item.getModelo())) {
                throw new RegistroDuplicadoException("Já existe um item com este nome e modelo.");
            }
        }

        return itemDAO.update(item);
    }

    public boolean delete(Long id) {
        itemDAO = new ItemDAO();

        return itemDAO.delete(id);
    }
}
