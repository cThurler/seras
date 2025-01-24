package com.seras.filtro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.seras.dao.ConnectionFactory;
import com.seras.model.Usuario;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class LogFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ip = request.getRemoteAddr();
        String pagina = httpRequest.getRequestURI();
        LocalDateTime dataHora = LocalDateTime.now();

        HttpSession session = httpRequest.getSession(false);
        Integer idUsuario = null;
        if (session != null && session.getAttribute("usuario") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            idUsuario = usuario.getId();
        }

        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO log_acessos (ip, data_hora, pagina, id_usuario) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, ip);
                stmt.setTimestamp(2, Timestamp.valueOf(dataHora));
                stmt.setString(3, pagina);
                if (idUsuario != null) {
                    stmt.setInt(4, idUsuario);
                } else {
                    stmt.setNull(4, java.sql.Types.INTEGER);
                }
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
