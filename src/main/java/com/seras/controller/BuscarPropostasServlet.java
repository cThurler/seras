package com.seras.controller;

import java.io.IOException;
import java.util.List;

import com.seras.dao.ClienteDAO;
import com.seras.dao.PropostaDAO;
import com.seras.model.Cliente;
import com.seras.model.PropostaEmprestimo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BuscarPropostasServlet")
public class BuscarPropostasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarPorCpf(cpf);

            if (cliente == null) {
                request.setAttribute("mensagemErro", "Cliente não encontrado.");
                request.getRequestDispatcher("buscar_propostas.jsp").forward(request, response);
                return;
            }

            PropostaDAO propostaDAO = new PropostaDAO();
            List<PropostaEmprestimo> propostas = propostaDAO.buscarPropostasPendentesPorCliente(cliente.getId());

            request.setAttribute("cpf", cpf);
            request.setAttribute("propostas", propostas);
            request.getRequestDispatcher("buscar_propostas.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
