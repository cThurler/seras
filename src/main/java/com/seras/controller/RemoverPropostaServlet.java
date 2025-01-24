package com.seras.controller;

import java.io.IOException;

import com.seras.dao.PropostaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoverPropostaServlet")
public class RemoverPropostaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("proposta_id");

            PropostaDAO PropostaDao = new PropostaDAO();
            PropostaDao.deletarPorId(id);
            response.sendRedirect("sucesso.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

    }
}
