package br.com.fiap.bo;

import br.com.fiap.exception.RegistroNaoEncontradoException;
import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.dao.ParceriaDAO;
import br.com.fiap.to.ParceriaTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ParceriaBO {

    private ParceriaDAO parceriaDAO;

    public ArrayList<ParceriaTO> findAll() {
        parceriaDAO = new ParceriaDAO();
        ArrayList<ParceriaTO> parcerias = parceriaDAO.findAll();
        if (parcerias != null) {
            for (ParceriaTO parceria : parcerias) {
                if (parceria.getDataFim() != null && parceria.getDataFim().isBefore(LocalDate.now())) {
                    parceria.setStatus("INATIVA");
                }
            }
        }
        return parcerias;
    }

    public ParceriaTO findById(Long id) {
        parceriaDAO = new ParceriaDAO();
        ParceriaTO parceria = parceriaDAO.findById(id);
        if (parceria != null && parceria.getDataFim() != null
                && parceria.getDataFim().isBefore(LocalDate.now())) {
            parceria.setStatus("INATIVA");
        }
        return parceria;
    }


    public ParceriaTO save(ParceriaTO parceria) {
        parceriaDAO = new ParceriaDAO();
        if (parceria == null || parceria.getNome() == null || parceria.getNome().isBlank()
                || parceria.getTipo() == null || parceria.getCustoMensal() == null
                || parceria.getCustoMensal().signum() < 0) {
            throw new RegraNegocioException("Informe nome, tipo e custo mensal válido da parceria.");
        }
        if (parceria.getDataInicio() == null) {
            parceria.setDataInicio(LocalDate.now());
        }
        if (parceria.getStatus() == null) {
            parceria.setStatus("ATIVA");
        }
        if (!"INFLUENCER".equals(parceria.getTipo()) && !"MARCA".equals(parceria.getTipo())) {
            throw new RegraNegocioException("Tipo de parceria inválido.");
        }
        if (!"ATIVA".equals(parceria.getStatus()) && !"INATIVA".equals(parceria.getStatus())) {
            throw new RegraNegocioException("Status de parceria inválido.");
        }
        if (parceria.getDataFim() != null && parceria.getDataFim().isBefore(parceria.getDataInicio())) {
            throw new RegraNegocioException("A data final da parceria não pode ser anterior à inicial.");
        }
        if (parceria.getDataFim() != null && parceria.getDataFim().isBefore(LocalDate.now())) {
            parceria.setStatus("INATIVA");
        }
        parceria.setNome(parceria.getNome().trim());
        return parceriaDAO.save(parceria);
    }

    public ParceriaTO update(ParceriaTO parceria) {
        parceriaDAO = new ParceriaDAO();
        if (parceria == null || parceria.getNome() == null || parceria.getNome().isBlank()
                || parceria.getTipo() == null || parceria.getCustoMensal() == null
                || parceria.getCustoMensal().signum() < 0) {
            throw new RegraNegocioException("Informe nome, tipo e custo mensal válido da parceria.");
        }
        if (parceria.getId() == null) {
            throw new RegraNegocioException("ID da parceria é obrigatório.");
        }
        ParceriaTO atual = parceriaDAO.findById(parceria.getId());
        if (atual == null || atual.getId() == null) {
            throw new RegistroNaoEncontradoException("Parceria não encontrada.");
        }
        if (parceria.getDataInicio() == null) {
            parceria.setDataInicio(atual.getDataInicio());
        }
        if (parceria.getStatus() == null) {
            parceria.setStatus(atual.getStatus());
        }
        if (!"INFLUENCER".equals(parceria.getTipo()) && !"MARCA".equals(parceria.getTipo())) {
            throw new RegraNegocioException("Tipo de parceria inválido.");
        }
        if (!"ATIVA".equals(parceria.getStatus()) && !"INATIVA".equals(parceria.getStatus())) {
            throw new RegraNegocioException("Status de parceria inválido.");
        }
        if (parceria.getDataFim() != null && parceria.getDataFim().isBefore(parceria.getDataInicio())) {
            throw new RegraNegocioException("A data final da parceria não pode ser anterior à inicial.");
        }
        if (parceria.getDataFim() != null && parceria.getDataFim().isBefore(LocalDate.now())) {
            parceria.setStatus("INATIVA");
        }
        parceria.setNome(parceria.getNome().trim());
        return parceriaDAO.update(parceria);
    }

    public boolean delete(Long id) {
        parceriaDAO = new ParceriaDAO();

        return parceriaDAO.delete(id);
    }
}
