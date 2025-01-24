package com.seras.controller;

import java.io.IOException;

import com.seras.dao.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoverUsuarioServlet")
public class RemoverUsuarioServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private UsuarioDAO UsuarioDAO;

    @Override
    public void init() {
        UsuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
        	String nome = request.getParameter("nome");
            UsuarioDAO.deletarUsuario(nome);
            response.sendRedirect("ClienteController?action=list");
        } 
    }
}
