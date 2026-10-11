package br.com.fiap.dao;

import br.com.fiap.to.ItemTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ItemDAO {

    public ItemTO save(ItemTO item) {
        String sql = "insert into ITENS (nome, modelo_item, valor_pontos, tipo) "
                + "values (?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, item.getNome());
            ps.setString(2, item.getModelo());
            ps.setObject(3, item.getValorPontos(), Types.INTEGER);
            ps.setString(4, item.getTipo());
            if (ps.executeUpdate() > 0) {
                return item;
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
