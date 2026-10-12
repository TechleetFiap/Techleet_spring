package br.com.fiap.dao;

import br.com.fiap.to.ItemTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;

public class ItemDAO {

    public ArrayList<ItemTO> findAll() {
        ArrayList<ItemTO> itens = new ArrayList<>();
        String sql = "select * from ITENS order by id_item";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ItemTO item = new ItemTO();
                    item.setId(rs.getLong("id_item"));
                    item.setNome(rs.getString("nome"));
                    item.setModelo(rs.getString("modelo_item"));
                    item.setValorPontos(rs.getInt("valor_pontos"));
                    if (rs.wasNull()) {
                        item.setValorPontos(null);
                    }
                    item.setTipo(rs.getString("tipo"));
                    itens.add(item);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }

        return itens;
    }

    public ItemTO findById(Long id) {
        ItemTO item = new ItemTO();
        String sql = "select * from ITENS where id_item = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item.setId(rs.getLong("id_item"));
                item.setNome(rs.getString("nome"));
                item.setModelo(rs.getString("modelo_item"));
                item.setValorPontos(rs.getInt("valor_pontos"));
                if (rs.wasNull()) {
                    item.setValorPontos(null);
                }
                item.setTipo(rs.getString("tipo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
        return item;
    }

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

    public boolean delete(Long id) {
        String sql = "delete from ITENS where id_item = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
