package br.com.fiap.dao;

import br.com.fiap.to.InventarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class InventarioDAO {

    public ArrayList<InventarioTO> findAll() {
        ArrayList<InventarioTO> inventarios = new ArrayList<>();
        String sql = "select * from INVENTARIO order by id_inventario";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    InventarioTO inventario = new InventarioTO();
                    inventario.setId(rs.getLong("id_inventario"));
                    inventario.setIdUsuario(rs.getLong("id_usuario"));
                    inventario.setIdItem(rs.getLong("id_item"));
                    inventario.setOrigem(rs.getString("origem"));
                    inventario.setDataAquisicao(rs.getDate("data_aquisicao").toLocalDate());
                    inventarios.add(inventario);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return inventarios;
    }

    public InventarioTO findById(Long id) {
        InventarioTO inventario = new InventarioTO();
        String sql = "select * from INVENTARIO where id_inventario = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                inventario.setId(rs.getLong("id_inventario"));
                inventario.setIdUsuario(rs.getLong("id_usuario"));
                inventario.setIdItem(rs.getLong("id_item"));
                inventario.setOrigem(rs.getString("origem"));
                inventario.setDataAquisicao(rs.getDate("data_aquisicao").toLocalDate());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return inventario;
    }

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

    public InventarioTO update(InventarioTO inventario) {
        String sql = "update INVENTARIO set id_usuario=?, id_item=?, origem=?, data_aquisicao=? where id_inventario=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, inventario.getIdUsuario());
            ps.setLong(2, inventario.getIdItem());
            ps.setString(3, inventario.getOrigem());
            ps.setDate(4, inventario.getDataAquisicao() != null
                    ? Date.valueOf(inventario.getDataAquisicao()) : null);
            ps.setLong(5, inventario.getId());
            if (ps.executeUpdate() > 0) {
                return inventario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fazer o update: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "delete from INVENTARIO where id_inventario = ?";
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
