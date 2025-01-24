<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Cliente" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Atualizar Proposta</title>
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
                        <%
						if (session.getAttribute("usuario") == null) {
					%>
					<h1>Página restrita para administradores
					<% 
						}else{
					%>
    <h1>Atualizar Proposta</h1>
     <form action="BuscarAtualizarPropostasServlet" method="post">
    <label for="cpf">CPF do Cliente:</label>
    <input type="text" id="cpf" name="cpf" value="${cpf}" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
    <button type="submit">Buscar</button>
</form>
    <small><a href="index.jsp">Voltar a página inicial.</a></small>
<c:if test="${not empty propostas}">
<c:forEach var="proposta" items="${propostas}">
    
    <table border='1'>
    	<thead><tr><th>Proposta ${proposta.id}</th></tr></thead>
    	<tbody><tr><td>
    <form action="AtualizarPropostaServlet" method="post">
    	<input type="hidden" name="action" id="action" value="update">
    	<input type="hidden" name="id" id="id" value="${proposta.id}">
		<label for="cpf">Valor Solicitado:</label>
		<input type="number" id="valor_solicitado" name="valor_solicitado" value="${proposta.valorSolicitado}" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
        <br>

        <label for="nome">Id do Cliente:</label>
        <input type="number" name="cliente_id" id="cliente_id" value="${proposta.clienteId}" required>
        <br>
        
        <label for="nome">Numero de Parcelas:</label>
        <input type="number" name="numero_parcelas" id="numero_parcelas" value="${proposta.numeroParcelas}" required>
        <br>
        
        <label for="email">Data de Solicitacao:</label>
        <input type="string" name="data_solicitacao" id="data_solicitacao" value="${proposta.dataSolicitacao}" required>
        <br>
        
        <label for="email">Status:</label>
        <input type="text" name="status" id="status" value="${proposta.status}" required>
        <br>

        <button type="submit">Atualizar</button>
    </form>
    </td></tr>
    </tbody>
    </table>
    </c:forEach>
        <c:if test="${not empty param.mensagemErro}">
        <div style="color: red; font-weight: bold;">
            ${param.mensagemErro}
        </div>
    	</c:if>
</c:if>
					<% 
						}
					%>
</body>
</html>