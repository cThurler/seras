package com.seras.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

import com.seras.dao.PropostaDAO;
import com.seras.model.PropostaEmprestimo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AtualizarSenhaServlet")
public class AtualizarSenhaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PropostaEmprestimo proposta = new PropostaEmprestimo();
    	proposta.setId(Integer.parseInt(request.getParameter("id")));
    	proposta.setClienteId(Integer.parseInt(request.getParameter("cliente_id")));
    	
    	String dataSolicitacaoStr = request.getParameter("data_solicitacao");
    	Date dataSolicitacao = Date.valueOf(dataSolicitacaoStr);
    	proposta.setDataSolicitacao(dataSolicitacao);
    	
    	proposta.setNumeroParcelas(Integer.parseInt(request.getParameter("numero_parcelas")));
    	proposta.setValorSolicitado(Double.parseDouble(request.getParameter("valor_solicitado")));
    	proposta.setStatus(request.getParameter("status"));
        try {
        	PropostaDAO propostaDao = new PropostaDAO();
            propostaDao.atualizar(proposta);
        } catch (Exception e) {
            String url = "http://localhost:8080/seras/atualizar_proposta.jsp";
            String mensagemErro;

            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("cpf")) {
                mensagemErro = "Erro ao cadastrar cliente: CPF já cadastrado.";
            } else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                mensagemErro = "Erro ao cadastrar cliente: E-mail já cadastrado.";
            } else {
                throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
            }

            String referer = url;
            if (referer == null || referer.isEmpty()) {
                referer = url + "/atualizar_proposta.jsp";
            }

            response.sendRedirect(referer + "?mensagemErro=" + URLEncoder.encode(mensagemErro, "UTF-8"));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.jsp");
    }
}


