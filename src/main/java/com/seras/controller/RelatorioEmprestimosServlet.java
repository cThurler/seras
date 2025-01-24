package com.seras.controller;

import java.io.IOException;
import java.util.List;

import com.seras.dao.EmprestimoDAO;
import com.seras.model.Emprestimo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RelatorioEmprestimosServlet")
public class RelatorioEmprestimosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmprestimoDAO dao = new EmprestimoDAO();
        List<Emprestimo> emprestimos = dao.listarEmprestimosDetalhados();

        request.setAttribute("emprestimos", emprestimos);
        request.getRequestDispatcher("/relatorio.jsp").forward(request, response);
    }
}

