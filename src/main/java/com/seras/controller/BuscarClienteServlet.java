package com.seras.controller;

import java.io.IOException;

import com.seras.dao.ClienteDAO;
import com.seras.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BuscarClienteServlet")
public class BuscarClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String fonte = request.getHeader("Referer");
        fonte = fonte.substring(fonte.indexOf(request.getContextPath()) + request.getContextPath().length());
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarPorCpf(cpf);

            if (cliente == null) {
                request.setAttribute("mensagemErro", "Cliente não encontrado.");
                request.getRequestDispatcher(fonte).forward(request, response);
                return;
            }
            
            request.setAttribute("clienteBuscado", cliente);
            request.getRequestDispatcher(fonte).forward(request, response);
        } catch (Exception e) {
        	throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
    }
}
