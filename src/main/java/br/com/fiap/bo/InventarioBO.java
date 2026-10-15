package br.com.fiap.bo;

import br.com.fiap.dao.InventarioDAO;
import br.com.fiap.dao.ItemDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.InventarioTO;
import br.com.fiap.to.ItemTO;
import br.com.fiap.to.UsuarioTO;

import java.time.LocalDate;
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

        if ("COMPRA".equals(inventarioTO.getOrigem())) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioTO usuario = usuarioDAO.findById(inventarioTO.getIdUsuario());
            if (usuario == null) {
                throw new RuntimeException("Usuário não encontrado.");
            }

            ItemDAO itemDAO = new ItemDAO();
            ItemTO item = itemDAO.findById(inventarioTO.getIdItem());
            if (item == null) {
                throw new RuntimeException("Item não encontrado.");
            }
            if (item.getValorPontos() == null || item.getValorPontos() < 0) {
                throw new RuntimeException("Item sem preço válido para compra.");
            }

            ArrayList<InventarioTO> inventarios = inventarioDAO.findAll();
            if (inventarios == null) {
                throw new RuntimeException("Não foi possível consultar o inventário.");
            }
            for (InventarioTO cadastrado : inventarios) {
                if (cadastrado.getIdUsuario().equals(inventarioTO.getIdUsuario())
                        && cadastrado.getIdItem().equals(inventarioTO.getIdItem())) {
                    throw new RuntimeException("Usuário já possui este item.");
                }
            }
            if (usuario.getPontos() < item.getValorPontos()) {
                throw new RuntimeException("Pontos insuficientes para comprar o item.");
            }
            inventarioTO.setDataAquisicao(LocalDate.now());
            int valorPontos = item.getValorPontos();
            if (!usuarioDAO.alterarPontos(usuario.getId(), -valorPontos)) {
                throw new RuntimeException("Não foi possível descontar os pontos da compra.");
            }

            InventarioTO resultado = null;
            try {
                resultado = inventarioDAO.save(inventarioTO);
                return resultado;
            } finally {
                if (resultado == null && !usuarioDAO.alterarPontos(usuario.getId(), valorPontos)) {
                    throw new RuntimeException("A compra falhou e não foi possível devolver os pontos.");
                }
            }
        }

        return inventarioDAO.save(inventarioTO);
    }

    public InventarioTO update(InventarioTO inventario) {
        inventarioDAO = new InventarioDAO();

        return inventarioDAO.update(inventario);
    }

    public boolean delete(Long id) {
        inventarioDAO = new InventarioDAO();

        return inventarioDAO.delete(id);
    }
}
