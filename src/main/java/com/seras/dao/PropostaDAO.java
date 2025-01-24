package com.seras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seras.model.PropostaEmprestimo;

public class PropostaDAO {
    public void inserir(PropostaEmprestimo proposta) {
        String sql = "INSERT INTO proposta_emprestimo (cliente_id, valor_solicitado, numero_parcelas, data_solicitacao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, proposta.getClienteId());
            stmt.setDouble(2, proposta.getValorSolicitado());
            stmt.setInt(3, proposta.getNumeroParcelas());
            stmt.setDate(4, new java.sql.Date(proposta.getDataSolicitacao().getTime()));

            stmt.executeUpdate();
            
            stmt.close();
        	conn.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir proposta: " + e.getMessage());
        }
    }
    
    public void atualizarStatus(int propostaId, String novoStatus) {
        String sql = "UPDATE proposta_emprestimo SET status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, propostaId);
            stmt.executeUpdate();
            stmt.close();
        	conn.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar status da proposta: " + e.getMessage());
        }
    }
    
    public List<PropostaEmprestimo> buscarPropostasPendentesPorCliente(int clienteId) {
        String sql = "SELECT * FROM proposta_emprestimo WHERE cliente_id = ? AND status = 'pendente'";
        List<PropostaEmprestimo> propostas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PropostaEmprestimo proposta = new PropostaEmprestimo();
                proposta.setId(rs.getInt("id"));
                proposta.setClienteId(rs.getInt("cliente_id"));
                proposta.setValorSolicitado(rs.getDouble("valor_solicitado"));
                proposta.setNumeroParcelas(rs.getInt("numero_parcelas"));
                proposta.setDataSolicitacao(rs.getDate("data_solicitacao"));
                propostas.add(proposta);
            }
            stmt.close();
        	conn.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar propostas pendentes: " + e.getMessage());
        }
        

        return propostas;
    }
    public List<PropostaEmprestimo> buscarPropostasPorCliente(int clienteId) {
        String sql = "SELECT * FROM proposta_emprestimo WHERE cliente_id = ?";
        List<PropostaEmprestimo> propostas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PropostaEmprestimo proposta = new PropostaEmprestimo();
                proposta.setId(rs.getInt("id"));
                proposta.setClienteId(rs.getInt("cliente_id"));
                proposta.setValorSolicitado(rs.getDouble("valor_solicitado"));
                proposta.setNumeroParcelas(rs.getInt("numero_parcelas"));
                proposta.setDataSolicitacao(rs.getDate("data_solicitacao"));
                proposta.setStatus(rs.getString("status"));
                propostas.add(proposta);
            }
            stmt.close();
        	conn.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar propostas pendentes: " + e.getMessage());
        }
        

        return propostas;
    }
    public List<PropostaEmprestimo> buscarPropostasPendentesPorCpf(String cpf) {
        String sql = "SELECT p.* FROM proposta_emprestimo p " +
                     "JOIN cliente c ON p.cliente_id = c.id " +
                     "WHERE c.cpf = ? AND p.status = 'PENDENTE'";

        List<PropostaEmprestimo> propostas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PropostaEmprestimo proposta = new PropostaEmprestimo();
                    proposta.setId(rs.getInt("id"));
                    proposta.setClienteId(rs.getInt("cliente_id"));
                    proposta.setValorSolicitado(rs.getDouble("valor_solicitado"));
                    proposta.setNumeroParcelas(rs.getInt("numero_parcelas"));
                    proposta.setDataSolicitacao(rs.getDate("data_solicitacao"));
                    propostas.add(proposta);
                }
            }
            stmt.close();
        	conn.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar propostas pendentes: " + e.getMessage());
        }

        return propostas;
    }
    public void deletarPorId(String propostaid) {
    int id = Integer.parseInt(propostaid);
    String sql = "DELETE FROM proposta_emprestimo WHERE id = ?";
    
    try(Connection conn = ConnectionFactory.getConnection()){
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	stmt.setInt(1, id);
    	
    	stmt.execute();
    	
    	stmt.close();
    	conn.close();
    		
    	} catch (SQLException e) {
    	throw new RuntimeException(e.getMessage());
    }
    
    }
    
    public void atualizar(PropostaEmprestimo proposta) {
        String sql = "UPDATE proposta_emprestimo SET cliente_id = ?, valor_solicitado = ?, numero_parcelas = ?, data_solicitacao = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, proposta.getClienteId());
            stmt.setDouble(2, proposta.getValorSolicitado());
            stmt.setInt(3, proposta.getNumeroParcelas());
            stmt.setDate(4, new java.sql.Date(proposta.getDataSolicitacao().getTime()));
            stmt.setString(5, proposta.getStatus());
            stmt.setInt(6, proposta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

}
