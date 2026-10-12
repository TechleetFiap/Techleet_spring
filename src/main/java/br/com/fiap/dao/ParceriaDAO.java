package br.com.fiap.dao;

import br.com.fiap.to.ParceriaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class ParceriaDAO {

    public ArrayList<ParceriaTO> findAll() {
        ArrayList<ParceriaTO> parcerias = new ArrayList<>();
        String sql = "select * from PARCERIAS order by id_parceria";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ParceriaTO parceria = new ParceriaTO();
                    parceria.setId(rs.getLong("id_parceria"));
                    parceria.setTipo(rs.getString("tipo"));
                    parceria.setNome(rs.getString("nome"));
                    parceria.setStatus(rs.getString("status"));
                    parceria.setCustoMensal(rs.getBigDecimal("custo_mensal"));
                    parceria.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                    parceria.setDataFim(rs.getDate("data_fim") != null ? rs.getDate("data_fim").toLocalDate() : null);
                    parcerias.add(parceria);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return parcerias;
    }

    public ParceriaTO findById(Long id) {
        ParceriaTO parceria = new ParceriaTO();
        String sql = "select * from PARCERIAS where id_parceria = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                parceria.setId(rs.getLong("id_parceria"));
                parceria.setTipo(rs.getString("tipo"));
                parceria.setNome(rs.getString("nome"));
                parceria.setStatus(rs.getString("status"));
                parceria.setCustoMensal(rs.getBigDecimal("custo_mensal"));
                parceria.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                parceria.setDataFim(rs.getDate("data_fim") != null ? rs.getDate("data_fim").toLocalDate() : null);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return parceria;
    }

    public ParceriaTO save(ParceriaTO parceria) {
        String sql = "insert into PARCERIAS (tipo, nome, status, custo_mensal, data_inicio, "
                + "data_fim) values (?, ?, NVL(?, 'ATIVA'), ?, NVL(?, SYSDATE), ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, parceria.getTipo());
            ps.setString(2, parceria.getNome());
            ps.setString(3, parceria.getStatus());
            ps.setBigDecimal(4, parceria.getCustoMensal());
            ps.setDate(5, parceria.getDataInicio() != null
                    ? Date.valueOf(parceria.getDataInicio()) : null);
            ps.setDate(6, parceria.getDataFim() != null
                    ? Date.valueOf(parceria.getDataFim()) : null);
            if (ps.executeUpdate() > 0) {
                return parceria;
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
        String sql = "delete from PARCERIAS where id_parceria = ?";
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
