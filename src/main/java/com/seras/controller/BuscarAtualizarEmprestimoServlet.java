package com.seras.controller;

import java.io.IOException;
import java.util.List;
import com.seras.dao.EmprestimoDAO;
import com.seras.dao.ClienteDAO;
import com.seras.model.Cliente;
import com.seras.model.Emprestimo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BuscarAtualizarEmprestimoServlet")
public class BuscarAtualizarEmprestimoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");

        try {
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            List<Emprestimo> emprestimos = emprestimoDAO.listarPorCpf(cpf);

            request.setAttribute("cpf", cpf);
            request.setAttribute("emprestimos", emprestimos);
            request.getRequestDispatcher("atualizar_emprestimo.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos: " + e.getMessage());
        }
    }
}
