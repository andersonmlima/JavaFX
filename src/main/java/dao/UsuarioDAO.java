package dao;

import model.Usuario;
import util.Conexao;

import java.sql.*;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        this.connection = Conexao.conectar(); // Obtém a conexão com o banco
    }

    // Metodo para cadastrar um novo usuário
    public boolean cadastrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se o usuário foi inserido
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

    // Metodo para validar o login do usuário
    public boolean validarLogin(String email, String senha) {
        String query = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o usuário for encontrado
        } catch (SQLException e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            return false;
        }
    }

    // Metodo para buscar um usuário pelo email
    public Usuario buscarPorEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
        }
        return null;
    }
}