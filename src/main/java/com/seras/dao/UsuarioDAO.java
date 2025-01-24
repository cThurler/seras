package com.seras.dao;

import com.seras.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuario WHERE username = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    return usuario;
                }
            }
        } catch (Exception e) {
        	throw new RuntimeException("Erro ao deletar usuario: " + e.getMessage(), e);
        }
        return null;
    }
    public void deletarUsuario(String nome) {
        String deleteUsuarioSql = "DELETE FROM usuario WHERE username = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement deleteUsuarioStmt = connection.prepareStatement(deleteUsuarioSql)) {
        	deleteUsuarioStmt.setString(1, nome);
        	deleteUsuarioStmt.executeUpdate();
        	
        	deleteUsuarioStmt.close();
        	connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuario: " + e.getMessage(), e);
        }
    }
    public void atualizarSenha(String nome, String novaSenha) {
        String updateSql = "UPDATE usuario SET password = ? WHERE username = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(updateSql)) {
            stmt.setString(1, novaSenha); 
            stmt.setString(2, nome);  

            stmt.executeUpdate();
            
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a senha: " + e.getMessage(), e);
        }
    }
    public boolean cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (username, password) VALUES (?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}

