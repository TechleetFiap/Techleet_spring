package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<UsuarioTO> findall() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "select * from USUARIOS";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);){
           ResultSet rs = ps.executeQuery();
           if (rs != null) {
               while (rs.next()) {
                   UsuarioTO usuario = new UsuarioTO();
                   usuario.setId(rs.getLong("id"));
                   usuario.setNome(rs.getString("nome"));
                   usuario.setEmail(rs.getString("email"));
                   usuario.setSenha(rs.getString("senha"));
                   usuarios.add(usuario);
               }
               } else {
               return null;
           }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findById(Long id) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select * from USUARIOS where id = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            } else  {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public UsuarioTO save (UsuarioTO usuario) {
        String sql = "insert into USUARIOS(id_usuario, nome, email, senha, data_nascimento, pontos) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, usuario.getId());
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

    public boolean delete(Long id) {
        String sql = "delete from USUARIOS where id = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
