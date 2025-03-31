package dao;

import model.Cartao;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {

    private Connection connection;

    public CartaoDAO() {
        this.connection = Conexao.conectar(); // Obtém a conexão com o banco
    }

    // Metodo para cadastrar um novo cartão
    public boolean cadastrarCartao(Cartao cartao) {
        String query = "INSERT INTO cartoes (usuario_id, nome_cartao, numero_cartao, bandeira, limite) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartao.getUsuarioId());
            stmt.setString(2, cartao.getNomeCartao());
            stmt.setString(3, cartao.getNumeroCartao());
            stmt.setString(4, cartao.getBandeira());
            stmt.setDouble(5, cartao.getLimite());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se o cartão foi inserido
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cartão: " + e.getMessage());
            return false;
        }
    }

    // Metodo para listar todos os cartões de um usuário
    public List<Cartao> listarCartoesPorUsuario(int usuarioId) {
        String query = "SELECT * FROM cartoes WHERE usuario_id = ?";
        List<Cartao> cartoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cartao cartao = new Cartao();
                cartao.setId(rs.getInt("id"));
                cartao.setUsuarioId(rs.getInt("usuario_id"));
                cartao.setNomeCartao(rs.getString("nome_cartao"));
                cartao.setNumeroCartao(rs.getString("numero_cartao"));
                cartao.setBandeira(rs.getString("bandeira"));
                cartao.setLimite(rs.getDouble("limite"));
                cartoes.add(cartao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar cartões: " + e.getMessage());
        }
        return cartoes;
    }
}
