<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Usuario" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página inicial</title>
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
    <form action="TemaServlet" method="post">
        <label for="tema">Escolha o Tema:</label>
        <select name="tema" id="tema">
            <option value="light" <c:if test="${tema == 'light'}">selected</c:if>>Claro</option>
            <option value="dark" <c:if test="${tema == 'dark'}">selected</c:if>>Escuro</option>
        </select>
        <button type="submit">Aplicar Tema</button>
    </form>
    <table border="1" width="300px">
            <thead>
                <tr>
                    <th>Navegação</th>
                </tr>
            </thead>
            <tbody>
                    <%
						if (session.getAttribute("usuario") == null) {
					%>
					<tr><td><a href="login.jsp">Login</a></td></tr>
					<%
						}
					%>
					<%
						if (session.getAttribute("usuario") == null) {
					%>
 						<tr><td><a href="buscar_emprestimo.jsp">Buscar Emprestimos</a></td></tr>
 						<tr><td><a href="buscar_propostas.jsp">Buscar Propostas</a></td></tr>
 						<tr><td><a href="buscar_cliente.jsp">Buscar Cliente</a></td></tr>
 					<%
						}
					%>
 						<tr><td><a href="cadastrar_proposta.jsp">Cadastrar Proposta</a></td></tr>
 						<tr><td><a href="cadastrar_cliente.jsp">Cadastrar Cliente</a></td></tr>
            </tbody>
   </table>
   
                    <%
						if (session.getAttribute("usuario") != null) {
					%>
		<table border='1' width="300px" style="margin:5px 0px">
        	<thead><tr><th>Navegação Admin</th></tr></thead>
        	 			<tr><td><a href="buscar_emprestimo.jsp">Buscar Emprestimos</a></td></tr>
 						<tr><td><a href="buscar_propostas.jsp">Buscar Propostas</a></td></tr>
 						<tr><td><a href="buscar_cliente.jsp">Buscar Cliente</a></td></tr>
 						<tr><td><a href="atualizar_cliente.jsp">Atualizar Cliente</a></td></tr>
 						<tr><td><a href="atualizar_proposta.jsp">Atualizar Proposta</a></td></tr>
 						<tr><td><a href="atualizar_emprestimo.jsp">Atualizar Emprestimo</a></td></tr>
 						<tr><td><a href="atualizar_senha_usuario.jsp">Atualizar Senha de Usuário</a></td></tr>
 						<tr><td><a href="cadastrar_usuario.jsp">Cadastrar Admin</a></td></tr>
 						<tr><td><a href="deletar_usuario.jsp">Deletar Admin</a></td></tr>
 						<tr><td><a href="relatorio.jsp">Visualizar relatório global</a></td></tr>
 						
        </table>
        <table border='1' width="100px">
        	<thead><tr><th>Logout</th></tr></thead>
        	<tbody><tr><td><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></td></tr></tbody>
        </table>
                    <%
						}
					%>
</body>
</html>
