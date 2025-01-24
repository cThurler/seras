<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Propostas</title>
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
    <h1>Buscar Propostas Pendentes</h1>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
    <c:set var="cpf" value="${param.cpf}" />

    <form action="BuscarPropostasServlet" method="post">
    <label for="cpf">CPF do Cliente:</label>
    <input type="text" id="cpf" name="cpf" value="${cpf}" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
    <button type="submit">Buscar</button>
</form>


    <c:if test="${not empty propostas}">
        <h2>Propostas Pendentes</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Valor Solicitado</th>
                    <th>Parcelas</th>
                    <th>Data Solicitação</th>
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
                <c:forEach var="proposta" items="${propostas}">
                    <tr>
                        <td>${proposta.id}</td>
                        <td>${proposta.valorSolicitado}</td>
                        <td>${proposta.numeroParcelas}</td>
                        <td>${proposta.dataSolicitacao}</td>
                        <%
						    if (session.getAttribute("usuario") != null) {
						%>
						    <td>
						        <form action="AprovarOuNegarPropostaServlet" method="post" style="display: inline;">
						            <input type="hidden" name="cpf" value="${param.cpf}" />
						            <input type="hidden" name="proposta_id" value="${proposta.id}" />
						            <input type="hidden" name="acao" value="aprovar" />
						            <input type="hidden" name="valor_solicitado" value="${proposta.valorSolicitado}" />
						            <input type="hidden" name="numero_parcelas" value="${proposta.numeroParcelas}" />
						            <input type="number" step="0.01" name="taxa_juros_mensal" id="taxa_juros_mensal" required placeholder="Taxa de Juros (%)" />
						            <button type="submit">Aprovar</button>
						        </form>
						        <form action="AprovarOuNegarPropostaServlet" method="post" style="display: inline;">
						            <input type="hidden" name="cpf" value="${param.cpf}" />
						            <input type="hidden" name="proposta_id" value="${proposta.id}" />
						            <input type="hidden" name="acao" value="negar" />
						            <button type="submit">Negar</button>
						        </form>
						        <form action="RemoverPropostaServlet" method="post" style="display: inline;">
						            <input type="hidden" name="proposta_id" value="${proposta.id}" />
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
