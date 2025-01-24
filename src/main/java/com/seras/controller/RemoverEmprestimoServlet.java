package com.seras.controller;

import java.io.IOException;

import com.seras.dao.EmprestimoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoverEmprestimoServlet")
public class RemoverEmprestimoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("emprestimo_id");

            EmprestimoDAO EmprestimoDao = new EmprestimoDAO();
            EmprestimoDao.deletarPorId(id);
            response.sendRedirect("sucesso.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

    }
}
