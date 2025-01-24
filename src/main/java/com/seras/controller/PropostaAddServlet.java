package com.seras.controller;

import com.seras.dao.PropostaDAO;
import com.seras.dao.ClienteDAO; // Assumindo que você tem um ClienteDAO para buscar cliente por CPF
import com.seras.model.PropostaEmprestimo;
import com.seras.model.Cliente; // Classe Cliente para representar o cliente

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/PropostaAddServlet")
public class PropostaAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Date dateNow = new Date();
            // Captura o CPF enviado pelo formulário
            String cpf = request.getParameter("cpf");

            // Usa o CPF para buscar o cliente e obter o cliente_id
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarPorCpf(cpf); // Método que busca o cliente pelo CPF

            if (cliente == null) {
                // Caso o cliente não seja encontrado, redireciona para erro
                response.sendRedirect("erro.jsp");
                return;
            }

            // Obtém os outros parâmetros da proposta
            double valorSolicitado = Double.parseDouble(request.getParameter("valor_solicitado"));
            int numeroParcelas = Integer.parseInt(request.getParameter("numero_parcelas"));

            // Cria a proposta e associa o clienteId
            PropostaEmprestimo proposta = new PropostaEmprestimo();
            proposta.setClienteId(cliente.getId()); // Agora usa o clienteId encontrado
            proposta.setValorSolicitado(valorSolicitado);
            proposta.setNumeroParcelas(numeroParcelas);
            proposta.setDataSolicitacao(dateNow);

            // Salva a proposta no banco
            PropostaDAO dao = new PropostaDAO();
            dao.inserir(proposta);

            // Redireciona para a lista de propostas
            response.sendRedirect("sucesso.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

    }
}
