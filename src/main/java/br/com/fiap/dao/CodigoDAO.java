package br.com.fiap.dao;

import br.com.fiap.to.CodigoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CodigoDAO {

    public CodigoTO save (CodigoTO codigo) {
        String sql = "insert into CODIGOS(id_codigo, codigo_resgate, status, data_validade, "
                + "data_resgate, id_item, id_parceria) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, codigo.getId());
            ps.setString(2, codigo.getCodigoResgate());
            ps.setString(3, codigo.getStatus());
            ps.setDate(4, codigo.getDataValidade() != null
                    ? Date.valueOf(codigo.getDataValidade()) : null);
            ps.setDate(5, codigo.getDataResgate() != null
                    ? Date.valueOf(codigo.getDataResgate()) : null);
            ps.setInt(6, codigo.getIdItem());
            ps.setInt(7, codigo.getIdParceria());
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
