package br.com.fiap.dao;

import br.com.fiap.to.AvatarTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvatarDAO {

    public AvatarTO save(AvatarTO avatar) {
        String sql = "insert into AVATARES (nome, id_cabelo, id_roupa_cima_int, id_roupa_cima_ext, id_roupa_baixo, id_calcado, id_acessorio, id_usuario) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, avatar.getNome());
            ps.setInt(2, avatar.getIdCabelo());
            ps.setInt(3, avatar.getIdRoupaCimaInt());
            ps.setInt(4, avatar.getIdRoupaCimaExt());
            ps.setInt(5, avatar.getIdRoupaBaixo());
            ps.setInt(6, avatar.getIdCalcado());
            ps.setInt(7, avatar.getIdAcessorio());
            ps.setInt(8, avatar.getIdUsuario());
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
