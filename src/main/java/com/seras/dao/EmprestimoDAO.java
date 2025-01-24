package com.seras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seras.model.Cliente;
import com.seras.model.Emprestimo;

public class EmprestimoDAO {
	public void inserirEmprestimo(Emprestimo emprestimo) {
	    String sql = "INSERT INTO emprestimo (proposta_id, valor_aprovado, numero_parcelas, saldo_devedor, data_inicio, taxa_juros_mensal) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, emprestimo.getPropostaId());
	        stmt.setDouble(2, emprestimo.getValorAprovado());
	        stmt.setInt(3, emprestimo.getNumeroParcelas());
	        stmt.setDouble(4, emprestimo.getSaldoDevedor());
	        stmt.setDate(5, new java.sql.Date(emprestimo.getDataInicio().getTime()));
	        stmt.setDouble(6, emprestimo.getTaxaJurosMensal());
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao inserir empréstimo: " + e.getMessage());
	    }
	}
    public List<Emprestimo> listarPorCpf(String cpf) {
        String sql = "SELECT e.id,e.proposta_id,e.valor_aprovado,e.numero_parcelas,e.saldo_devedor,e.data_inicio,e.taxa_juros_mensal "
        		+ "FROM emprestimo e JOIN proposta_emprestimo p ON(p.id = e.proposta_id) JOIN cliente c ON(p.cliente_id = c.id) "
        		+ "WHERE c.cpf = ?";
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql);) {
        	stmt.setString(1, cpf);
        	ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setPropostaId(rs.getInt("proposta_id"));
                emprestimo.setValorAprovado(rs.getDouble("valor_aprovado"));
                emprestimo.setNumeroParcelas(rs.getInt("numero_parcelas"));
                emprestimo.setSaldoDevedor(rs.getDouble("saldo_devedor"));
                emprestimo.setDataInicio(rs.getDate("data_inicio"));
                emprestimo.setTaxaJurosMensal(rs.getDouble("taxa_juros_mensal"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar emprestimos: " + e.getMessage());
        }
        return emprestimos;
    }
    
    public void deletarPorId(String emprestimoid) {
    int id = Integer.parseInt(emprestimoid);
    String sql = "DELETE FROM emprestimo WHERE id = ?";
    
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
    

    public void atualizar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET valor_aprovado = ?, numero_parcelas = ?, saldo_devedor = ?, data_inicio = ?, taxa_juros_mensal = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, emprestimo.getValorAprovado());
            stmt.setInt(2, emprestimo.getNumeroParcelas());
            stmt.setDouble(3, emprestimo.getSaldoDevedor());
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataInicio().getTime()));
            stmt.setDouble(5, emprestimo.getTaxaJurosMensal());
            stmt.setInt(6, emprestimo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }
    
    public List<Emprestimo> listarEmprestimosDetalhados() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT e.id AS emprestimo_id, e.valor_aprovado, e.numero_parcelas, e.saldo_devedor, e.data_inicio, e.taxa_juros_mensal, "
                   + "p.id AS proposta_id, p.valor_solicitado, p.numero_parcelas AS proposta_parcelas, p.status, "
                   + "c.id AS cliente_id, c.nome AS cliente_nome, c.cpf, c.email "
                   + "FROM emprestimo e "
                   + "JOIN proposta_emprestimo p ON e.proposta_id = p.id "
                   + "JOIN cliente c ON p.cliente_id = c.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("emprestimo_id"));
                emprestimo.setValorAprovado(rs.getDouble("valor_aprovado"));
                emprestimo.setNumeroParcelas(rs.getInt("numero_parcelas"));
                emprestimo.setSaldoDevedor(rs.getDouble("saldo_devedor"));
                emprestimo.setDataInicio(rs.getDate("data_inicio"));
                emprestimo.setTaxaJurosMensal(rs.getDouble("taxa_juros_mensal"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente_id"));
                cliente.setNome(rs.getString("cliente_nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                emprestimo.setCliente(cliente);

                emprestimos.add(emprestimo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
}

