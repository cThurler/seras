<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.seras.model.Cliente" %>
<%@ page import="com.seras.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Deletar Usuario</title>
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
						    			if (session.getAttribute("usuario") != null) {
										%>
    	  <h1>Deletar Usuario</h1>
          <form action="RemoverUsuarioServlet" method="post" style="display:inline;">
                   <input type="hidden" name="action" value="delete">
                   <label for="nome">Insira o nome</label>
                   <input type="text" name="nome">
          <button type="submit">Deletar</button>
          </form>
          						        <%
						    			} else {
										%>
										<h1>Página restrita para administradores.</h1>
										<%
						    			}
										%>
</body>
</html>
