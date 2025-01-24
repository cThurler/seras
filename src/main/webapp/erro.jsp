<!DOCTYPE html>
<%@ page import="com.seras.model.Usuario" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Erro</title>
    <%
    String tema = "light";

    Usuario usuario = null;
    if (session != null && session.getAttribute("usuario") != null) {
        usuario = (Usuario) session.getAttribute("usuario");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("tema_" + usuario.getId())) {
                    tema = cookie.getValue();
                    break;
                }
            }
        }
    }

    request.setAttribute("tema", tema);
%>
<style>
                .tema-escuro { 
                    background-color: black; 
                    color: white; 
                }
                .tema-claro { 
                    background-color: white; 
                    color: black; 
                }

    </style>
</head>
<body class="${tema == 'dark' ? 'tema-escuro' : 'tema-claro'}">
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<h1>Erro</h1>
	<p><%= errorMessage != null ? errorMessage : "Ocorreu um erro desconhecido." %></p>
	<small><a href="index.jsp">Voltar a página inicial.</a></small>
</body>
</html>
