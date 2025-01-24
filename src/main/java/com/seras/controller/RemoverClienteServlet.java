package com.seras.controller;

import java.io.IOException;

import com.seras.dao.ClienteDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoverClienteServlet")
public class RemoverClienteServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private com.seras.dao.ClienteDAO ClienteDAO;

    @Override
    public void init() {
    	ClienteDAO = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	String cpf = request.getParameter("cpf");
        	ClienteDAO.deletarPorCpf(cpf);
            response.sendRedirect("sucesso.jsp");
        } 
    }
