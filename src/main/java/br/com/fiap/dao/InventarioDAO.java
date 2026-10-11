package br.com.fiap.dao;

import br.com.fiap.to.InventarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventarioDAO {

    public InventarioTO save(InventarioTO inventario) {
        String sql = "insert into INVENTARIO (id_usuario, id_item, origem, "
                + "data_aquisicao) values (?, ?, ?, NVL(?, SYSDATE))";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, inventario.getIdUsuario());
            ps.setLong(2, inventario.getIdItem());
            ps.setString(3, inventario.getOrigem());
            ps.setDate(4, inventario.getDataAquisicao() != null
                    ? Date.valueOf(inventario.getDataAquisicao()) : null);
            if (ps.executeUpdate() > 0) {
                return inventario;
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
