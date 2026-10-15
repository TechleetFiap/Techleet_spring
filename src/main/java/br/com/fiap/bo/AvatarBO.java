package br.com.fiap.bo;

import br.com.fiap.dao.AvatarDAO;
import br.com.fiap.to.AvatarTO;

import br.com.fiap.dao.InventarioDAO;
import br.com.fiap.dao.ItemDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.InventarioTO;
import br.com.fiap.to.ItemTO;
import br.com.fiap.to.UsuarioTO;
import java.util.ArrayList;

public class AvatarBO {

    private AvatarDAO avatarDAO;

    public ArrayList<AvatarTO> findAll() {
        avatarDAO = new AvatarDAO();
        // aqui se implementa a regra de negocio
        return avatarDAO.findAll();
    }

    public AvatarTO findById(Long id) {
        avatarDAO = new AvatarDAO();
        // aqui se implementa a regra de negocio
        return avatarDAO.findById(id);
    }


    public AvatarTO save(AvatarTO avatar) {
        avatarDAO = new AvatarDAO();
        if (avatar == null || avatar.getIdUsuario() == null
                || avatar.getNome() == null || avatar.getNome().isBlank()) {
            throw new RuntimeException("Nome e usuário do avatar são obrigatórios.");
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioTO usuario = usuarioDAO.findById(avatar.getIdUsuario());
        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        ArrayList<AvatarTO> avatares = avatarDAO.findAll();
        if (avatares == null) {
            throw new RuntimeException("Não foi possível consultar os avatares.");
        }
        for (AvatarTO cadastrado : avatares) {
            if (cadastrado.getIdUsuario().equals(avatar.getIdUsuario())) {
                throw new RuntimeException("Usuário já possui um avatar.");
            }
        }
        InventarioDAO inventarioDAO = new InventarioDAO();
        ArrayList<InventarioTO> inventarios = inventarioDAO.findAll();
        if (inventarios == null) {
            throw new RuntimeException("Não foi possível consultar o inventário.");
        }
        Long[] ids = {avatar.getIdCabelo(), avatar.getIdRoupaCimaInt(), avatar.getIdRoupaCimaExt(),
                avatar.getIdRoupaBaixo(), avatar.getIdCalcado(), avatar.getIdAcessorio()};
        String[] modelos = {"CABELO", "ROUPA DE CIMA INTERNA", "ROUPA DE CIMA EXTERNA",
                "ROUPA DE BAIXO", "CALCADO", "ACESSORIO"};
        ItemDAO itemDAO = new ItemDAO();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null) {
                throw new RuntimeException("Todos os itens do avatar são obrigatórios.");
            }
            ItemTO item = itemDAO.findById(ids[i]);
            if (item == null || item.getId() == null || !item.verificarCompatibilidade(modelos[i])) {
                throw new RuntimeException("Item incompatível com a posição " + modelos[i] + ".");
            }
            boolean possui = false;
            for (InventarioTO inventario : inventarios) {
                if (inventario.getIdUsuario().equals(avatar.getIdUsuario())
                        && inventario.getIdItem().equals(ids[i])) {
                    possui = true;
                    break;
                }
            }
            if (!possui) {
                throw new RuntimeException("Usuário não possui o item para " + modelos[i] + ".");
            }
        }
        avatar.setNome(avatar.getNome().trim());
        return avatarDAO.save(avatar);
    }

    public AvatarTO update(AvatarTO avatar) {
        avatarDAO = new AvatarDAO();
        if (avatar == null || avatar.getIdUsuario() == null
                || avatar.getNome() == null || avatar.getNome().isBlank()) {
            throw new RuntimeException("Nome e usuário do avatar são obrigatórios.");
        }
        if (avatar.getId() == null) {
            throw new RuntimeException("ID do avatar é obrigatório.");
        }
        AvatarTO atual = avatarDAO.findById(avatar.getId());
        if (atual == null || atual.getId() == null) {
            throw new RuntimeException("Avatar não encontrado.");
        }
        if (!atual.getIdUsuario().equals(avatar.getIdUsuario())) {
            throw new RuntimeException("Não é permitido trocar o usuário do avatar.");
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioTO usuario = usuarioDAO.findById(avatar.getIdUsuario());
        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        ArrayList<AvatarTO> avatares = avatarDAO.findAll();
        if (avatares == null) {
            throw new RuntimeException("Não foi possível consultar os avatares.");
        }
        for (AvatarTO cadastrado : avatares) {
            if (cadastrado.getIdUsuario().equals(avatar.getIdUsuario())
                    && !cadastrado.getId().equals(avatar.getId())) {
                throw new RuntimeException("Usuário já possui um avatar.");
            }
        }
        InventarioDAO inventarioDAO = new InventarioDAO();
        ArrayList<InventarioTO> inventarios = inventarioDAO.findAll();
        if (inventarios == null) {
            throw new RuntimeException("Não foi possível consultar o inventário.");
        }
        Long[] ids = {avatar.getIdCabelo(), avatar.getIdRoupaCimaInt(), avatar.getIdRoupaCimaExt(),
                avatar.getIdRoupaBaixo(), avatar.getIdCalcado(), avatar.getIdAcessorio()};
        String[] modelos = {"CABELO", "ROUPA DE CIMA INTERNA", "ROUPA DE CIMA EXTERNA",
                "ROUPA DE BAIXO", "CALCADO", "ACESSORIO"};
        ItemDAO itemDAO = new ItemDAO();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null) {
                throw new RuntimeException("Todos os itens do avatar são obrigatórios.");
            }
            ItemTO item = itemDAO.findById(ids[i]);
            if (item == null || item.getId() == null || !item.verificarCompatibilidade(modelos[i])) {
                throw new RuntimeException("Item incompatível com a posição " + modelos[i] + ".");
            }
            boolean possui = false;
            for (InventarioTO inventario : inventarios) {
                if (inventario.getIdUsuario().equals(avatar.getIdUsuario())
                        && inventario.getIdItem().equals(ids[i])) {
                    possui = true;
                    break;
                }
            }
            if (!possui) {
                throw new RuntimeException("Usuário não possui o item para " + modelos[i] + ".");
            }
        }
        avatar.setNome(avatar.getNome().trim());
        return avatarDAO.update(avatar);
    }

    public boolean delete(Long id) {
        avatarDAO = new AvatarDAO();

        return avatarDAO.delete(id);
    }
}
