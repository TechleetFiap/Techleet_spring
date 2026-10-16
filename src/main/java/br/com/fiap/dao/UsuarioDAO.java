package br.com.fiap.dao;

import br.com.fiap.exception.PersistenciaException;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "select * from USUARIOS order by id_usuario";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(rs.getLong("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    usuario.setPontos(rs.getInt("pontos"));
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
        String sql = "select * from USUARIOS where id_usuario = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setPontos(rs.getInt("pontos"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public UsuarioTO findByEmail(String email) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select * from USUARIOS where lower(email) = lower(?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setPontos(rs.getInt("pontos"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por e-mail: " + e.getMessage());
            throw new PersistenciaException("Erro ao consultar usuário para login.", e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "insert into USUARIOS (nome, email, senha, data_nascimento, pontos) values(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            ps.setInt(5, usuario.getPontos());
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

    public boolean alterarPontos(Long id, int quantidade) {
        String sql = "update USUARIOS set pontos = pontos + ? where id_usuario = ? and pontos + ? >= 0";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, quantidade);
            ps.setLong(2, id);
            ps.setInt(3, quantidade);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar pontos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "update USUARIOS set nome=?, email=?, senha=?, data_nascimento=? where id_usuario=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            ps.setLong(5, usuario.getId());
            if (ps.executeUpdate() > 0) {
                return usuario;
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
        String sql = "delete from USUARIOS where id_usuario = ?";
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
