package br.com.fiap.dao;

import br.com.fiap.to.ItemTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAO {

    public ItemTO save(ItemTO item) {
        String sql = "insert into ITENS(id_item, nome, modelo_item, valor_pontos, tipo) "
                + "values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, item.getId());
            ps.setString(2, item.getNome());
            ps.setString(3, item.getModelo());
            ps.setInt(4, item.getValorPontos());
            ps.setString(5, item.getTipo());
            if (ps.executeUpdate() > 0) {
                return item;
            } else  {
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
