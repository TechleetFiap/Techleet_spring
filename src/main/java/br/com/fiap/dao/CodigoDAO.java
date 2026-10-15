package br.com.fiap.dao;

import br.com.fiap.to.CodigoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class CodigoDAO {

    public ArrayList<CodigoTO> findAll() {
        ArrayList<CodigoTO> codigos = new ArrayList<>();
        String sql = "select * from CODIGOS order by id_codigo";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CodigoTO codigo = new CodigoTO();
                    codigo.setId(rs.getLong("id_codigo"));
                    codigo.setCodigoResgate(rs.getString("codigo_resgate"));
                    codigo.setDataValidade(rs.getDate("data_validade").toLocalDate());
                    codigo.setStatus(rs.getString("status"));
                    codigo.setDataResgate(rs.getDate("data_resgate") != null ? rs.getDate("data_resgate").toLocalDate() : null);
                    codigo.setIdItem(rs.getLong("id_item"));
                    codigo.setIdParceria(rs.getLong("id_parceria"));
                    codigos.add(codigo);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return codigos;
    }

    public CodigoTO findById(Long id) {
        CodigoTO codigo = new CodigoTO();
        String sql = "select * from CODIGOS where id_codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                codigo.setId(rs.getLong("id_codigo"));
                codigo.setCodigoResgate(rs.getString("codigo_resgate"));
                codigo.setDataValidade(rs.getDate("data_validade").toLocalDate());
                codigo.setStatus(rs.getString("status"));
                codigo.setDataResgate(rs.getDate("data_resgate") != null ? rs.getDate("data_resgate").toLocalDate() : null);
                codigo.setIdItem(rs.getLong("id_item"));
                codigo.setIdParceria(rs.getLong("id_parceria"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return codigo;
    }

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

    public CodigoTO update(CodigoTO codigo) {
        String sql = "update CODIGOS set codigo_resgate=?, status=?, data_validade=?, data_resgate=?, id_item=?, id_parceria=? where id_codigo=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo.getCodigoResgate());
            ps.setString(2, codigo.getStatus());
            ps.setDate(3, codigo.getDataValidade() != null
                    ? Date.valueOf(codigo.getDataValidade()) : null);
            ps.setDate(4, codigo.getDataResgate() != null
                    ? Date.valueOf(codigo.getDataResgate()) : null);
            ps.setLong(5, codigo.getIdItem());
            ps.setLong(6, codigo.getIdParceria());
            ps.setLong(7, codigo.getId());
            if (ps.executeUpdate() > 0) {
                return codigo;
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
        String sql = "delete from CODIGOS where id_codigo = ?";
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
