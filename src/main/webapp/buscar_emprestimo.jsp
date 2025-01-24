<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Cliente" %>
<%@ page import="com.seras.model.Emprestimo" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Deletar Emprestimo</title>
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
    <h1>Buscar Emprestimo</h1>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
    <c:set var="cpf" value="${param.cpf}" />

    <form action="BuscarEmprestimoServlet" method="post">
    <label for="cpf">CPF do Cliente:</label>
    <input type="text" id="cpf" name="cpf" value="${cpf}" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
    <button type="submit">Buscar</button>
</form>
		<c:if test="${not empty emprestimos}">
          <table border="1">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Valor Aprovado</th>
                    <th>Numero de parcelas</th>
                    <th>Saldo Devedor</th>
                    <th>Data Inicio</th>
                    <th>Taxa de Juros</th>
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
                <c:forEach var="emprestimos" items="${emprestimos}">
                    <tr>
                        <td>${emprestimos.id}</td>
                        <td>${emprestimos.valorAprovado}</td>
                        <td>${emprestimos.numeroParcelas}</td>
                        <td>${emprestimos.saldoDevedor}</td>
                        <td>${emprestimos.dataInicio}</td>
                        <td>${emprestimos.taxaJurosMensal}</td>
						                <%
						    			if (session.getAttribute("usuario") != null) {
										%>
						    <td>
						        <form action="RemoverEmprestimoServlet" method="post" style="display: inline;">
						            <input type="hidden" name="emprestimo_id" value="${emprestimos.id}" />
						            <button type="submit">Remover</button>
						        </form>
						    </td>
						    						<%
						    						}
													%>

                    </tr>
                </c:forEach>
            </tbody>
           </table>
        </c:if>
</body>
</html>
