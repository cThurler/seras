package com.seras.controller;

import java.io.IOException;
import java.util.List;

import com.seras.dao.ClienteDAO;
import com.seras.dao.EmprestimoDAO;
import com.seras.model.Cliente;
import com.seras.model.Emprestimo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BuscarEmprestimoServlet")
public class BuscarEmprestimoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarPorCpf(cpf);

            if (cliente == null) {
                request.setAttribute("mensagemErro", "Cliente não encontrado.");
                request.getRequestDispatcher("buscar_emprestimo.jsp").forward(request, response);
                return;
            }

            EmprestimoDAO EmprestimoDAO = new EmprestimoDAO();
            List<Emprestimo> emprestimos = EmprestimoDAO.listarPorCpf(cpf);

            request.setAttribute("cpf", cpf);
            request.setAttribute("emprestimos", emprestimos);
            request.getRequestDispatcher("buscar_emprestimo.jsp").forward(request, response);
        } catch (Exception e) {
        	throw new RuntimeException("Erro ao listar emprestimo: " + e.getMessage());
        }
    }
}
