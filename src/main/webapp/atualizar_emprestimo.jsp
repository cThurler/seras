<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Emprestimo" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Atualizar Empréstimo</title>
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
</head>
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
<body class="${tema == 'dark' ? 'tema-escuro' : 'tema-claro'}">
                        <%
						if (session.getAttribute("usuario") == null) {
					%>
					<h1>Página restrita para administradores
					<% 
						}else{
					%>
    <h1>Atualizar Empréstimo</h1>
    
    <form action="BuscarAtualizarEmprestimoServlet" method="post">
        <label for="cpf">CPF do Cliente:</label>
        <input type="text" id="cpf" name="cpf" value="${cpf}" required maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="XXX.XXX.XXX-XX" />
        <button type="submit">Buscar</button>
    </form>
    <small><a href="index.jsp">Voltar à página inicial.</a></small>
    
    <c:if test="${not empty emprestimos}">
        <c:forEach var="emprestimo" items="${emprestimos}">
            <table border='1'>
                <thead><tr><th>Empréstimo ${emprestimo.id}</th></tr></thead>
                <tbody><tr><td>
                    <form action="AtualizarEmprestimoServlet" method="post">
                        <input type="hidden" name="action" id="action" value="update">
                        <input type="hidden" name="id" id="id" value="${emprestimo.id}">
                        
                        <label for="valor_aprovado">Valor Aprovado:</label>
                        <input type="number" id="valor_aprovado" name="valor_aprovado" value="${emprestimo.valorAprovado}" required>
                        <br>

                        <label for="numero_parcelas">Número de Parcelas:</label>
                        <input type="number" name="numero_parcelas" id="numero_parcelas" value="${emprestimo.numeroParcelas}" required>
                        <br>

                        <label for="saldo_devedor">Saldo Devedor:</label>
                        <input type="number" name="saldo_devedor" id="saldo_devedor" value="${emprestimo.saldoDevedor}" required>
                        <br>

                        <label for="data_inicio">Data Início:</label>
                        <input type="date" name="data_inicio" id="data_inicio" value="${emprestimo.dataInicio}" required>
                        <br>

                        <label for="taxa_juros_mensal">Taxa de Juros Mensal:</label>
                        <input type="number" name="taxa_juros_mensal" id="taxa_juros_mensal" value="${emprestimo.taxaJurosMensal}" required>
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
