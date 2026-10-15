package br.com.fiap.bo;

import br.com.fiap.dao.InventarioDAO;
import br.com.fiap.dao.ItemDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.InventarioTO;
import br.com.fiap.to.ItemTO;
import br.com.fiap.to.UsuarioTO;

import java.time.LocalDate;
import br.com.fiap.dao.AvatarDAO;
import br.com.fiap.to.AvatarTO;
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


    public InventarioTO save(InventarioTO inventarioTO) {
        inventarioDAO = new InventarioDAO();
        if (inventarioTO == null || inventarioTO.getIdUsuario() == null || inventarioTO.getIdItem() == null) {
            throw new RuntimeException("Usuário e item são obrigatórios.");
        }
        if (!"COMPRA".equals(inventarioTO.getOrigem())) {
            throw new RuntimeException("A aquisição de itens está disponível apenas por compra.");
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioTO usuario = usuarioDAO.findById(inventarioTO.getIdUsuario());
        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        ItemDAO itemDAO = new ItemDAO();
        ItemTO item = itemDAO.findById(inventarioTO.getIdItem());
        if (item == null || item.getId() == null) {
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

    public InventarioTO update(InventarioTO inventario) {
        inventarioDAO = new InventarioDAO();
        if (inventario == null || inventario.getId() == null) {
            throw new RuntimeException("ID do inventário é obrigatório.");
        }
        InventarioTO atual = inventarioDAO.findById(inventario.getId());
        if (atual == null || atual.getId() == null) {
            throw new RuntimeException("Registro do inventário não encontrado.");
        }
        if (!atual.getIdUsuario().equals(inventario.getIdUsuario())
                || !atual.getIdItem().equals(inventario.getIdItem())
                || !atual.getOrigem().equals(inventario.getOrigem())
                || (inventario.getDataAquisicao() != null
                && !atual.getDataAquisicao().equals(inventario.getDataAquisicao()))) {
            throw new RuntimeException("Não é permitido alterar os dados de uma aquisição.");
        }
        return atual;
    }

    public boolean delete(Long id) {
        inventarioDAO = new InventarioDAO();
        InventarioTO atual = inventarioDAO.findById(id);
        if (atual == null || atual.getId() == null) {
            return false;
        }
        AvatarDAO avatarDAO = new AvatarDAO();
        ArrayList<AvatarTO> avatares = avatarDAO.findAll();
        if (avatares == null) {
            throw new RuntimeException("Não foi possível consultar os avatares.");
        }
        for (AvatarTO avatar : avatares) {
            if (atual.getIdUsuario().equals(avatar.getIdUsuario())
                    && (atual.getIdItem().equals(avatar.getIdCabelo())
                    || atual.getIdItem().equals(avatar.getIdRoupaCimaInt())
                    || atual.getIdItem().equals(avatar.getIdRoupaCimaExt())
                    || atual.getIdItem().equals(avatar.getIdRoupaBaixo())
                    || atual.getIdItem().equals(avatar.getIdCalcado())
                    || atual.getIdItem().equals(avatar.getIdAcessorio()))) {
                throw new RuntimeException("Não é permitido excluir um item equipado no avatar.");
            }
        }
        return inventarioDAO.delete(id);
    }
}
