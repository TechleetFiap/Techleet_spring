package br.com.fiap.dao;

import br.com.fiap.to.AvatarTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvatarDAO {

    public AvatarTO save (AvatarTO avatar) {
        String sql = "insert into AVATARES(id_avatar, nome, id_cabelo, id_roupa_cima_int, id_roupa_cima_ext, id_roupa_baixo, id_calcado, id_acessorio, id_usuario) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, avatar.getId());
            ps.setString(2, avatar.getNome());
            ps.setInt(3, avatar.getIdCabelo());
            ps.setInt(4, avatar.getIdRoupaCimaInt());
            ps.setInt(5, avatar.getIdRoupaCimaExt());
            ps.setInt(6, avatar.getIdRoupaBaixo());
            ps.setInt(7, avatar.getIdCalcado());
            ps.setInt(8, avatar.getIdAcessorio());
            ps.setInt(9, avatar.getIdUsuario());
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
}
