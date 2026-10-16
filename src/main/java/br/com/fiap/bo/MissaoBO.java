package br.com.fiap.bo;

import br.com.fiap.exception.RegistroNaoEncontradoException;
import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.dao.MissaoDAO;
import br.com.fiap.to.MissaoTO;

import java.util.ArrayList;

public class MissaoBO {

    private MissaoDAO missaoDAO;

    public ArrayList<MissaoTO> findAll() {
        missaoDAO = new MissaoDAO();
        // aqui se implementa a regra de negocio
        return missaoDAO.findAll();
    }

    public MissaoTO findById(Long id) {
        missaoDAO = new MissaoDAO();
        // aqui se implementa a regra de negocio
        return missaoDAO.findById(id);
    }


    public MissaoTO save(MissaoTO missao) {
        missaoDAO = new MissaoDAO();
        if (missao == null || missao.getTitulo() == null || missao.getTitulo().isBlank()
                || missao.getDescricao() == null || missao.getDescricao().isBlank()
                || missao.getPontos() < 0) {
            throw new RegraNegocioException("Informe título, descrição e pontos válidos da missão.");
        }
        if (!"DIARIA".equals(missao.getTipo()) && !"SEMANAL".equals(missao.getTipo())
                && !"ESPECIAL".equals(missao.getTipo())) {
            throw new RegraNegocioException("Tipo de missão inválido.");
        }
        return missaoDAO.save(missao);
    }

    public MissaoTO update(MissaoTO missao) {
        missaoDAO = new MissaoDAO();
        if (missao == null || missao.getTitulo() == null || missao.getTitulo().isBlank()
                || missao.getDescricao() == null || missao.getDescricao().isBlank()
                || missao.getPontos() < 0) {
            throw new RegraNegocioException("Informe título, descrição e pontos válidos da missão.");
        }
        if (!"DIARIA".equals(missao.getTipo()) && !"SEMANAL".equals(missao.getTipo())
                && !"ESPECIAL".equals(missao.getTipo())) {
            throw new RegraNegocioException("Tipo de missão inválido.");
        }
        if (missao.getId() == null) {
            throw new RegistroNaoEncontradoException("Missão não encontrada.");
        }
        MissaoTO atual = missaoDAO.findById(missao.getId());
        if (atual == null || atual.getId() == null) {
            throw new RegistroNaoEncontradoException("Missão não encontrada.");
        }
        return missaoDAO.update(missao);
    }

    public boolean delete(Long id) {
        missaoDAO = new MissaoDAO();

        return missaoDAO.delete(id);
    }
}
