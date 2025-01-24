package com.seras.controller;

import com.seras.dao.UsuarioDAO;
import com.seras.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar dados do formulário
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");

        // Criar o objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(nome);
        usuario.setPassword(senha);

        // Persistir o usuário no banco de dados
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean cadastroSucesso = usuarioDAO.cadastrarUsuario(usuario);

        // Redirecionar para página de sucesso ou erro
        if (cadastroSucesso) {
            response.sendRedirect("sucesso.jsp");
        } else {
            response.sendRedirect("erro.jsp");
        }
    }
}
