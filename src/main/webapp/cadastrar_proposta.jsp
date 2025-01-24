<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Cliente" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Proposta de Emprestimo</title>
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
    <h1>Cadastrar Proposta de Emprestimo</h1>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
    <form action="PropostaAddServlet" method="post">
		<label for="cpf">CPF do Cliente:</label>
		<input type="text" id="cpf" name="cpf" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
        <br>

        <label for="valor">Valor Solicitado:</label>
        <input type="number" step="0.01" name="valor_solicitado" id="valor" required>
        <br>

        <label for="parcelas">Número de Parcelas:</label>
        <input type="number" name="numero_parcelas" id="parcelas" required>
        <br>

        <button type="submit">Cadastrar</button>
    </form>
</body>
</html>
