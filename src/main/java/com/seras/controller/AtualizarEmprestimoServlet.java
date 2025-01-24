package com.seras.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.seras.dao.EmprestimoDAO;
import com.seras.model.Emprestimo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AtualizarEmprestimoServlet")
public class AtualizarEmprestimoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String valorAprovado = request.getParameter("valor_aprovado");
        String numeroParcelas = request.getParameter("numero_parcelas");
        String saldoDevedor = request.getParameter("saldo_devedor");
        String dataInicio = request.getParameter("data_inicio");
        String taxaJurosMensal = request.getParameter("taxa_juros_mensal");

        try {
            int emprestimoId = Integer.parseInt(id);
            double valorAprovadoDouble = Double.parseDouble(valorAprovado);
            int numeroParcelasInt = Integer.parseInt(numeroParcelas);
            double saldoDevedorDouble = Double.parseDouble(saldoDevedor);
            java.util.Date dataInicioDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
            double taxaJurosMensalDouble = Double.parseDouble(taxaJurosMensal);

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(emprestimoId);
            emprestimo.setValorAprovado(valorAprovadoDouble);
            emprestimo.setNumeroParcelas(numeroParcelasInt);
            emprestimo.setSaldoDevedor(saldoDevedorDouble);
            emprestimo.setDataInicio(dataInicioDate);
            emprestimo.setTaxaJurosMensal(taxaJurosMensalDouble);

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            emprestimoDAO.atualizar(emprestimo);

            response.sendRedirect("sucesso.jsp");
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("mensagemErro", "Erro ao processar os dados do empréstimo: " + e.getMessage());
            request.getRequestDispatcher("atualizar_emprestimo.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }
}
