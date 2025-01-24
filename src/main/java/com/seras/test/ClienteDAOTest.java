package com.seras.test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.seras.dao.ClienteDAO;
import com.seras.model.Cliente;

public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        clienteDAO = new ClienteDAO();
        cliente = new Cliente();
        cliente.setNome("João da Silva");
        cliente.setCpf("12345678900");
        cliente.setEmail("joao.silva@example.com");
    }

    @Test
    public void testCadastrarCliente() {
        boolean resultado = clienteDAO.inserir(cliente);
        assertTrue(resultado, "O cliente deveria ser cadastrado com sucesso.");
    }

    @Test
    public void testListarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        assertNotNull(clientes, "A lista de clientes não pode ser nula.");
        assertTrue(clientes.size() > 0, "Deveria haver pelo menos um cliente.");
    }

    @Test
    public void testAtualizarCliente() {
        cliente.setNome("João da Silva Atualizado");
        boolean resultado = clienteDAO.atualizar(cliente);
        assertTrue(resultado, "O cliente deveria ser atualizado com sucesso.");
    }

    @Test
    public void testDeletarCliente() {
        boolean resultado = clienteDAO.deletarPorCpf(cliente.getCpf());
        assertTrue(resultado, "O cliente deveria ser deletado com sucesso.");
    }
}
