package br.com.fiap.dao;

import br.com.fiap.to.CodigoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CodigoDAO {

    public CodigoTO save(CodigoTO codigo) {
        String sql = "insert into CODIGOS (codigo_resgate, status, data_validade, "
                + "data_resgate, id_item, id_parceria) values (?, NVL(?, 'DISPONIVEL'), ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo.getCodigoResgate());
            ps.setString(2, codigo.getStatus());
            ps.setDate(3, codigo.getDataValidade() != null
                    ? Date.valueOf(codigo.getDataValidade()) : null);
            ps.setDate(4, codigo.getDataResgate() != null
                    ? Date.valueOf(codigo.getDataResgate()) : null);
            ps.setLong(5, codigo.getIdItem());
            ps.setLong(6, codigo.getIdParceria());
            if (ps.executeUpdate() > 0) {
                return codigo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
