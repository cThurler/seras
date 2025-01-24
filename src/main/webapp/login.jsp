<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
    <h1>Login</h1>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
    <form action="LoginServlet" method="post">
        <label for="username">Usuário:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Senha:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Entrar</button>
    </form>
    <c:if test="${param.error == '1'}">
        <p style="color: red;">Usuário ou senha inválidos.</p>
    </c:if>
</body>
</html>
