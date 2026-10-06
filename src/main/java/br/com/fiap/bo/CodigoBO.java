package br.com.fiap.bo;

import br.com.fiap.dao.CodigoDAO;
import br.com.fiap.to.CodigoTO;

public class CodigoBO {

    private CodigoDAO codigoDAO;

    public CodigoTO save (CodigoTO codigo) {
        codigoDAO = new CodigoDAO();

        return codigoDAO.save(codigo);
    }
}
