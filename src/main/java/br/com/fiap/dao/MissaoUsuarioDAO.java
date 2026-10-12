package br.com.fiap.dao;

import br.com.fiap.to.MissaoUsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class MissaoUsuarioDAO {

    public ArrayList<MissaoUsuarioTO> findAll() {
        ArrayList<MissaoUsuarioTO> missoesUsuario = new ArrayList<>();
        String sql = "select * from MISSOES_USUARIO order by id_missao_usuario";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    MissaoUsuarioTO missaoUsuario = new MissaoUsuarioTO();
                    missaoUsuario.setId(rs.getLong("id_missao_usuario"));
                    missaoUsuario.setStatus(rs.getString("status"));
                    missaoUsuario.setDataRealizacao(rs.getDate("data_realizacao") != null ? rs.getDate("data_realizacao").toLocalDate() : null);
                    missaoUsuario.setIdUsuario(rs.getLong("id_usuario"));
                    missaoUsuario.setIdMissao(rs.getLong("id_missao"));
                    missaoUsuario.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                    missaoUsuario.setDataFim(rs.getDate("data_fim").toLocalDate());
                    missoesUsuario.add(missaoUsuario);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return missoesUsuario;
    }

    public MissaoUsuarioTO findById(Long id) {
        MissaoUsuarioTO missaoUsuario = new MissaoUsuarioTO();
        String sql = "select * from MISSOES_USUARIO where id_missao_usuario = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                missaoUsuario.setId(rs.getLong("id_missao_usuario"));
                missaoUsuario.setStatus(rs.getString("status"));
                missaoUsuario.setDataRealizacao(rs.getDate("data_realizacao") != null ? rs.getDate("data_realizacao").toLocalDate() : null);
                missaoUsuario.setIdUsuario(rs.getLong("id_usuario"));
                missaoUsuario.setIdMissao(rs.getLong("id_missao"));
                missaoUsuario.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                missaoUsuario.setDataFim(rs.getDate("data_fim").toLocalDate());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return missaoUsuario;
    }

    public MissaoUsuarioTO save(MissaoUsuarioTO missaoUsuario) {
        String sql = "insert into MISSOES_USUARIO (status, data_realizacao, id_usuario, id_missao, "
                + "data_inicio, data_fim) values (NVL(?, 'DISPONIVEL'), ?, ?, ?, NVL(?, SYSDATE), ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, missaoUsuario.getStatus());
            ps.setDate(2, missaoUsuario.getDataRealizacao() != null
                    ? Date.valueOf(missaoUsuario.getDataRealizacao()) : null);
            ps.setLong(3, missaoUsuario.getIdUsuario());
            ps.setLong(4, missaoUsuario.getIdMissao());
            ps.setDate(5, missaoUsuario.getDataInicio() != null
                    ? Date.valueOf(missaoUsuario.getDataInicio()) : null);
            ps.setDate(6, missaoUsuario.getDataFim() != null
                    ? Date.valueOf(missaoUsuario.getDataFim()) : null);
            if (ps.executeUpdate() > 0) {
                return missaoUsuario;
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
        String sql = "delete from MISSOES_USUARIO where id_missao_usuario = ?";
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
