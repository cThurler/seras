package com.seras.model;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int propostaId;
    private double valorAprovado;
    private int numeroParcelas;
    private double saldoDevedor;
    private Date dataInicio;
    private double taxaJurosMensal;
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropostaId() {
        return propostaId;
    }

    public void setPropostaId(int propostaId) {
        this.propostaId = propostaId;
    }

    public double getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(double valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getTaxaJurosMensal() {
        return taxaJurosMensal;
    }

    public void setTaxaJurosMensal(double taxaJurosMensal) {
        this.taxaJurosMensal = taxaJurosMensal;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    public void calcularSaldoDevedor() {
        this.saldoDevedor = this.valorAprovado + (this.valorAprovado * ((this.taxaJurosMensal / 100) * this.numeroParcelas));
    }
}
