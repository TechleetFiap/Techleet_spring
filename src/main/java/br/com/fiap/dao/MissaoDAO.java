package br.com.fiap.dao;

import br.com.fiap.to.MissaoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class MissaoDAO {

    public ArrayList<MissaoTO> findAll() {
        ArrayList<MissaoTO> missoes = new ArrayList<>();
        String sql = "select * from MISSOES order by id_missao";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    MissaoTO missao = new MissaoTO();
                    missao.setId(rs.getLong("id_missao"));
                    missao.setTitulo(rs.getString("titulo"));
                    missao.setDescricao(rs.getString("descricao"));
                    missao.setPontos(rs.getInt("pontos"));
                    missao.setTipo(rs.getString("tipo"));
                    missoes.add(missao);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return missoes;
    }

    public MissaoTO findById(Long id) {
        MissaoTO missao = new MissaoTO();
        String sql = "select * from MISSOES where id_missao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                missao.setId(rs.getLong("id_missao"));
                missao.setTitulo(rs.getString("titulo"));
                missao.setDescricao(rs.getString("descricao"));
                missao.setPontos(rs.getInt("pontos"));
                missao.setTipo(rs.getString("tipo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return missao;
    }

    public MissaoTO save(MissaoTO missao) {
        String sql = "insert into MISSOES (titulo, descricao, pontos, tipo) values (?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, missao.getTitulo());
            ps.setString(2, missao.getDescricao());
            ps.setInt(3, missao.getPontos());
            ps.setString(4, missao.getTipo());
            if (ps.executeUpdate() > 0) {
                return missao;
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
        String sql = "delete from MISSOES where id_missao = ?";
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
