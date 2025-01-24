package com.seras.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.seras.dao.ClienteDAO;
import com.seras.model.Cliente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listarClientes(request, response);
                break;
            case "atualizar":
                atualizarCliente(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("insert".equals(action)) {
            inserirCliente(request, response);
        } else if ("update".equals(action)) {
            atualizarCliente(request, response);
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.listar();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("proposta_add.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setEndereco(request.getParameter("endereco"));
        try {
            clienteDAO.inserir(cliente);
        } catch (Exception e) {
            String url = "http://localhost:8080/seras/cadastrar_cliente.jsp";
            String mensagemErro;

            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("cpf")) {
                mensagemErro = "Erro ao cadastrar cliente: CPF já cadastrado.";
            } else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                mensagemErro = "Erro ao cadastrar cliente: E-mail já cadastrado.";
            } else {
                throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
            }

            String referer = url;
            if (referer == null || referer.isEmpty()) {
                referer = url + "/cadastrar_cliente.jsp";
            }

            response.sendRedirect(referer + "?mensagemErro=" + URLEncoder.encode(mensagemErro, "UTF-8"));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.jsp");
    }


    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setEndereco(request.getParameter("endereco"));
        try {
            clienteDAO.atualizar(cliente);
        } catch (Exception e) {
            String url = "http://localhost:8080/seras/atualizar_cliente.jsp";
            String mensagemErro;

            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("cpf")) {
                mensagemErro = "Erro ao cadastrar cliente: CPF já cadastrado.";
            } else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                mensagemErro = "Erro ao cadastrar cliente: E-mail já cadastrado.";
            } else {
                throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
            }

            String referer = url;
            if (referer == null || referer.isEmpty()) {
                referer = url + "/atualizar_cliente.jsp";
            }

            response.sendRedirect(referer + "?mensagemErro=" + URLEncoder.encode(mensagemErro, "UTF-8"));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/sucesso.jsp");
    }
}
