package com.seras.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.seras.dao.PropostaDAO;
import com.seras.model.PropostaEmprestimo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AtualizarPropostaServlet")
public class AtualizarPropostaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo parâmetros do formulário
        String id = request.getParameter("id");
        String clienteId = request.getParameter("cliente_id");
        String valorSolicitado = request.getParameter("valor_solicitado");
        String numeroParcelas = request.getParameter("numero_parcelas");
        String dataSolicitacao = request.getParameter("data_solicitacao");
        String status = request.getParameter("status");

        try {
            int propostaId = Integer.parseInt(id);
            int clienteIdInt = Integer.parseInt(clienteId);
            double valorSolicitadoDouble = Double.parseDouble(valorSolicitado);
            int numeroParcelasInt = Integer.parseInt(numeroParcelas);
            java.util.Date dataSolicitacaoDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataSolicitacao);


            PropostaEmprestimo proposta = new PropostaEmprestimo();
            proposta.setId(propostaId);
            proposta.setClienteId(clienteIdInt);
            proposta.setValorSolicitado(valorSolicitadoDouble);
            proposta.setNumeroParcelas(numeroParcelasInt);
            proposta.setDataSolicitacao(dataSolicitacaoDate);
            proposta.setStatus(status);

            PropostaDAO propostaDAO = new PropostaDAO();
            propostaDAO.atualizar(proposta);


            response.sendRedirect("sucesso.jsp");
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("mensagemErro", "Erro ao processar os dados da proposta: " + e.getMessage());
            request.getRequestDispatcher("atualizar_proposta.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar proposta: " + e.getMessage());
        }
    }
}
