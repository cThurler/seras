package com.seras.controller;

import java.io.IOException;
import java.util.Date;

import com.seras.dao.EmprestimoDAO;
import com.seras.dao.PropostaDAO;
import com.seras.model.Emprestimo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/AprovarOuNegarPropostaServlet")
public class AprovarOuNegarPropostaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int propostaId = Integer.parseInt(request.getParameter("proposta_id"));
        String acao = request.getParameter("acao");

        try {
            PropostaDAO propostaDAO = new PropostaDAO();

            if ("aprovar".equals(acao)) {
            	String cpf = (request.getParameter("cpf"));
                double taxaJurosMensal = Double.parseDouble(request.getParameter("taxa_juros_mensal"));
                double valorSolicitado = Double.parseDouble(request.getParameter("valor_solicitado"));
                int numeroParcelas = Integer.parseInt(request.getParameter("numero_parcelas"));

                propostaDAO.atualizarStatus(propostaId, "APROVADO");

                EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setPropostaId(propostaId);
                emprestimo.setTaxaJurosMensal(taxaJurosMensal);
                emprestimo.setValorAprovado(valorSolicitado);
                emprestimo.setNumeroParcelas(numeroParcelas);
                emprestimo.setSaldoDevedor(valorSolicitado);
                emprestimo.setDataInicio(new Date());
                emprestimo.calcularSaldoDevedor();
                emprestimoDAO.inserirEmprestimo(emprestimo);

                response.sendRedirect("buscar_propostas.jsp?cpf=" + cpf);
            } else if ("negar".equals(acao)) {
            	String cpf = (request.getParameter("cpf"));
                propostaDAO.atualizarStatus(propostaId, "NEGADO");
                response.sendRedirect("buscar_propostas.jsp?cpf=" + cpf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
