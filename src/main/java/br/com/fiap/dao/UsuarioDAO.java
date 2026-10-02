package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public UsuarioTO save (UsuarioTO usuario) {
        String sql = "insert into USUARIOS(id_usuario, nome, email, senha, data_nascimento, pontos) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setDate(5, Date.valueOf(usuario.getDataNascimento()));
            ps.setInt(6, usuario.getPontos());
            if (ps.executeUpdate() > 0) {
                return usuario;
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
