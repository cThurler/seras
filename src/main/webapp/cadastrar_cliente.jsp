<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Cliente" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Cliente</title>
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
    <h1>Cadastrar Cliente</h1>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
    <form action="ClienteController" method="post">
    	<input type="hidden" name="action" id="action" value="insert">
		<label for="cpf">CPF do Cliente:</label>
		<input type="text" id="cpf" name="cpf" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
        <br>

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" required>
        <br>
        
        <label for="email">E-mail:</label>
        <input type="text" name="email" id="email" required>
        <br>
        
        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone" id="telefone" required>
        <br>
        
        <label for="endereco">Endereco:</label>
        <input type="text" name="endereco" id="endereco" required>
        <br>


        <button type="submit">Cadastrar</button>
    </form>
    <c:if test="${not empty param.mensagemErro}">
        <div style="color: red; font-weight: bold;">
            ${param.mensagemErro}
        </div>
    </c:if>
</body>
</html>