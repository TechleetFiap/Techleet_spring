package br.com.fiap.dao;

import br.com.fiap.to.AvatarTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class AvatarDAO {

    public ArrayList<AvatarTO> findAll() {
        ArrayList<AvatarTO> avatares = new ArrayList<>();
        String sql = "select * from AVATARES order by id_avatar";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    AvatarTO avatar = new AvatarTO();
                    avatar.setId(rs.getLong("id_avatar"));
                    avatar.setNome(rs.getString("nome"));
                    avatar.setIdCabelo(rs.getLong("id_cabelo"));
                    avatar.setIdRoupaCimaInt(rs.getLong("id_roupa_cima_int"));
                    avatar.setIdRoupaCimaExt(rs.getLong("id_roupa_cima_ext"));
                    avatar.setIdRoupaBaixo(rs.getLong("id_roupa_baixo"));
                    avatar.setIdCalcado(rs.getLong("id_calcado"));
                    avatar.setIdAcessorio(rs.getLong("id_acessorio"));
                    avatar.setIdUsuario(rs.getLong("id_usuario"));
                    avatares.add(avatar);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return avatares;
    }

    public AvatarTO findById(Long id) {
        AvatarTO avatar = new AvatarTO();
        String sql = "select * from AVATARES where id_avatar = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                avatar.setId(rs.getLong("id_avatar"));
                avatar.setNome(rs.getString("nome"));
                avatar.setIdCabelo(rs.getLong("id_cabelo"));
                avatar.setIdRoupaCimaInt(rs.getLong("id_roupa_cima_int"));
                avatar.setIdRoupaCimaExt(rs.getLong("id_roupa_cima_ext"));
                avatar.setIdRoupaBaixo(rs.getLong("id_roupa_baixo"));
                avatar.setIdCalcado(rs.getLong("id_calcado"));
                avatar.setIdAcessorio(rs.getLong("id_acessorio"));
                avatar.setIdUsuario(rs.getLong("id_usuario"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return avatar;
    }

    public AvatarTO save(AvatarTO avatar) {
        String sql = "insert into AVATARES (nome, id_cabelo, id_roupa_cima_int, id_roupa_cima_ext, id_roupa_baixo, id_calcado, id_acessorio, id_usuario) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, avatar.getNome());
            ps.setLong(2, avatar.getIdCabelo());
            ps.setLong(3, avatar.getIdRoupaCimaInt());
            ps.setLong(4, avatar.getIdRoupaCimaExt());
            ps.setLong(5, avatar.getIdRoupaBaixo());
            ps.setLong(6, avatar.getIdCalcado());
            ps.setLong(7, avatar.getIdAcessorio());
            ps.setLong(8, avatar.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return avatar;
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
        String sql = "delete from AVATARES where id_avatar = ?";
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
