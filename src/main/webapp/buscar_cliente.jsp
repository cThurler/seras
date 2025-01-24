<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Cliente</title>
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
    <h1>Buscar Cliente</h1>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
    <c:set var="cpf" value="${param.cpf}" />

    <form action="BuscarClienteServlet" method="post">
    <label for="cpf">CPF do Cliente:</label>
    <input type="text" id="cpf" name="cpf" value="${cpf}" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
    <button type="submit">Buscar</button>
</form>

    <c:if test="${not empty mensagemErro}">
        <div style="color: red; font-weight: bold;">
            ${mensagemErro}
        </div>
    </c:if>
    <c:if test="${not empty clienteBuscado}">
        <h2>Cliente:</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>E-mail</th>
                    <th>Telefone</th>
                    <th>Endereço</th>
                    <%
						if (session.getAttribute("usuario") != null) {
					%>
                    <th>Ações</th>
                    <%
						}
					%>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>${clienteBuscado.id}</td>
                        <td>${clienteBuscado.nome}</td>
                        <td>${clienteBuscado.cpf}</td>
                        <td>${clienteBuscado.email}</td>
                        <td>${clienteBuscado.telefone}</td>
                        <td>${clienteBuscado.endereco}</td>
                        <%
						    if (session.getAttribute("usuario") != null) {
						%>
						    <td>
						        <form action="RemoverClienteServlet" method="post" style="display: inline;">
						            <input type="hidden" name="cpf" value="${clienteBuscado.cpf}" />
						            <button type="submit">Remover</button>
						        </form>
						    </td>
						<%
						    }
						%>
                    </tr>
            </tbody>
        </table>
    </c:if>
</body>
</html>
