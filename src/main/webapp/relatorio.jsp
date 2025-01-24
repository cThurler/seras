<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Relatório de Empréstimos</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
						                <%
						    			if (session.getAttribute("usuario") != null) {
										%>
    <h1>Relatório de Empréstimos</h1>

<c:if test="${empty emprestimos}">
        <form id="redirectForm" action="RelatorioEmprestimosServlet" method="POST">
            <noscript>
                <input type="submit" value="Carregar Relatório" />
            </noscript>
        </form>
        <script type="text/javascript">
            document.getElementById('redirectForm').submit();
        </script>
</c:if>
    <c:if test="${empty emprestimos}">
        <p>Nenhum empréstimo encontrado.</p>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Valor Aprovado</th>
                <th>Número de Parcelas</th>
                <th>Saldo Devedor</th>
                <th>Data Início</th>
                <th>Taxa de Juros</th>
                <th>Cliente</th>
                <th>CPF</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="emprestimo" items="${emprestimos}">
                <tr>
                    <td>${emprestimo.id}</td>
                    <td>${emprestimo.valorAprovado}</td>
                    <td>${emprestimo.numeroParcelas}</td>
                    <td>${emprestimo.saldoDevedor}</td>
                    <td>${emprestimo.dataInicio}</td>
                    <td>${emprestimo.taxaJurosMensal}</td>
                    <td>${emprestimo.cliente.nome}</td>
                    <td>${emprestimo.cliente.cpf}</td>
                    <td>${emprestimo.cliente.email}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
              						        <%
						    			} else {
										%>
										<h1>Página restrita para administradores.</h1>
										<%
						    			}
										%>
</body>
</html>
