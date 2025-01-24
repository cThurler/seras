package com.seras.controller;
import java.io.IOException;

import com.seras.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/TemaServlet")
public class TemaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter o tema escolhido
        String tema = request.getParameter("tema");

        // Obter o usuário logado na sessão
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            // Armazenar o tema no cookie
            Cookie cookieTema = new Cookie("tema_" + usuario.getId(), tema);
            cookieTema.setMaxAge(60 * 60 * 24 * 365); // O cookie vai durar 1 ano
            response.addCookie(cookieTema);

            // Redirecionar para a página inicial ou onde o tema será aplicado
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp"); // Caso usuário não esteja logado
        }
    }
}
