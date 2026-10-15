package br.com.fiap.bo;

import br.com.fiap.dao.MissaoUsuarioDAO;
import br.com.fiap.to.MissaoUsuarioTO;

import br.com.fiap.dao.MissaoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.MissaoTO;
import br.com.fiap.to.UsuarioTO;
import java.time.LocalDate;
import java.util.ArrayList;

public class MissaoUsuarioBO {

    private MissaoUsuarioDAO missaoUsuarioDAO;

    public ArrayList<MissaoUsuarioTO> findAll() {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        ArrayList<MissaoUsuarioTO> missoes = missaoUsuarioDAO.findAll();
        if (missoes != null) {
            for (MissaoUsuarioTO missao : missoes) {
                if (missao.getDataRealizacao() == null && missao.getDataFim().isBefore(LocalDate.now())) {
                    missao.setStatus("EXPIRADA");
                }
            }
        }
        return missoes;
    }

    public MissaoUsuarioTO findById(Long id) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        MissaoUsuarioTO missao = missaoUsuarioDAO.findById(id);
        if (missao != null && missao.getDataRealizacao() == null
                && missao.getDataFim().isBefore(LocalDate.now())) {
            missao.setStatus("EXPIRADA");
        }
        return missao;
    }


    public MissaoUsuarioTO save(MissaoUsuarioTO missaoUsuario) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        if (missaoUsuario == null || missaoUsuario.getIdUsuario() == null
                || missaoUsuario.getIdMissao() == null || missaoUsuario.getDataFim() == null) {
            throw new RuntimeException("Usuário, missão e data final são obrigatórios.");
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        MissaoDAO missaoDAO = new MissaoDAO();
        UsuarioTO usuario = usuarioDAO.findById(missaoUsuario.getIdUsuario());
        MissaoTO missao = missaoDAO.findById(missaoUsuario.getIdMissao());
        if (usuario == null || usuario.getId() == null || missao == null || missao.getId() == null) {
            throw new RuntimeException("Usuário ou missão não encontrado.");
        }
        if (missaoUsuario.getDataInicio() == null) {
            missaoUsuario.setDataInicio(LocalDate.now());
        }
        if (missaoUsuario.getDataFim().isBefore(missaoUsuario.getDataInicio())
                || missaoUsuario.getDataFim().isBefore(LocalDate.now())) {
            throw new RuntimeException("Período da missão inválido.");
        }
        if (missaoUsuario.getDataRealizacao() != null) {
            throw new RuntimeException("Cadastre a missão antes de concluí-la pelo update.");
        }
        ArrayList<MissaoUsuarioTO> missoes = missaoUsuarioDAO.findAll();
        if (missoes == null) {
            throw new RuntimeException("Não foi possível consultar as missões do usuário.");
        }
        for (MissaoUsuarioTO cadastrada : missoes) {
            if (cadastrada.getIdUsuario().equals(missaoUsuario.getIdUsuario())
                    && cadastrada.getIdMissao().equals(missaoUsuario.getIdMissao())
                    && !missaoUsuario.getDataInicio().isAfter(cadastrada.getDataFim())
                    && !missaoUsuario.getDataFim().isBefore(cadastrada.getDataInicio())) {
                throw new RuntimeException("Usuário já possui esta missão nesse período.");
            }
        }
        missaoUsuario.setStatus("DISPONIVEL");
        return missaoUsuarioDAO.save(missaoUsuario);
    }

    public MissaoUsuarioTO update(MissaoUsuarioTO missaoUsuario) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        if (missaoUsuario == null || missaoUsuario.getId() == null) {
            throw new RuntimeException("ID da missão do usuário é obrigatório.");
        }
        MissaoUsuarioTO atual = missaoUsuarioDAO.findById(missaoUsuario.getId());
        if (atual == null || atual.getId() == null) {
            throw new RuntimeException("Missão do usuário não encontrada.");
        }
        if (!atual.getIdUsuario().equals(missaoUsuario.getIdUsuario())
                || !atual.getIdMissao().equals(missaoUsuario.getIdMissao())
                || (missaoUsuario.getDataInicio() != null
                && !atual.getDataInicio().equals(missaoUsuario.getDataInicio()))
                || (missaoUsuario.getDataFim() != null
                && !atual.getDataFim().equals(missaoUsuario.getDataFim()))) {
            throw new RuntimeException("Não é permitido trocar o usuário, a missão ou o período cadastrado.");
        }
        if (atual.getDataRealizacao() != null) {
            throw new RuntimeException("Missão já concluída.");
        }
        LocalDate hoje = LocalDate.now();
        boolean concluir = missaoUsuario.getDataRealizacao() != null;
        if (concluir && (hoje.isBefore(atual.getDataInicio()) || hoje.isAfter(atual.getDataFim())
                || !"DISPONIVEL".equals(atual.getStatus()))) {
            throw new RuntimeException("A missão não está disponível para conclusão.");
        }
        missaoUsuario.setDataInicio(atual.getDataInicio());
        missaoUsuario.setDataFim(atual.getDataFim());
        missaoUsuario.setStatus(hoje.isAfter(atual.getDataFim()) ? "EXPIRADA" : atual.getStatus());
        if (!concluir) {
            return missaoUsuarioDAO.update(missaoUsuario);
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        MissaoDAO missaoDAO = new MissaoDAO();
        MissaoTO missao = missaoDAO.findById(atual.getIdMissao());
        UsuarioTO usuario = usuarioDAO.findById(atual.getIdUsuario());
        if (usuario == null || usuario.getId() == null || missao == null
                || missao.getId() == null || missao.getPontos() < 0) {
            throw new RuntimeException("Usuário ou missão inválido para receber os pontos.");
        }
        missaoUsuario.setDataRealizacao(hoje);
        int pontos = missao.getPontos();
        if (!usuarioDAO.alterarPontos(atual.getIdUsuario(), pontos)) {
            throw new RuntimeException("Não foi possível creditar os pontos da missão.");
        }
        MissaoUsuarioTO resultado = null;
        try {
            resultado = missaoUsuarioDAO.update(missaoUsuario);
            return resultado;
        } finally {
            if (resultado == null && !usuarioDAO.alterarPontos(atual.getIdUsuario(), -pontos)) {
                throw new RuntimeException("A conclusão falhou e não foi possível estornar os pontos.");
            }
        }
    }

    public boolean delete(Long id) {
        missaoUsuarioDAO = new MissaoUsuarioDAO();
        MissaoUsuarioTO atual = missaoUsuarioDAO.findById(id);
        if (atual == null || atual.getId() == null) {
            return false;
        }
        if (atual.getDataRealizacao() != null) {
            throw new RuntimeException("Não é permitido excluir uma missão já concluída.");
        }
        return missaoUsuarioDAO.delete(id);
    }
}
