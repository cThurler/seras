package com.seras.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.seras.model.Emprestimo;

class TesteSaldoDevedor {

    private Emprestimo emprestimo;

    @BeforeEach
    public void setUp() {
        emprestimo = new Emprestimo();
    }
	@Test
    public void testCalcularSaldoDevedor() {
        emprestimo.setValorAprovado(12000.0);
        emprestimo.setNumeroParcelas(12);    
        emprestimo.setTaxaJurosMensal(1.5);   
       
        emprestimo.calcularSaldoDevedor();


        double saldoEsperado = (12000.0+(12000.0 * (1.5 / 100) * 12));
        assertEquals(saldoEsperado, emprestimo.getSaldoDevedor(), "O saldo devedor deve ser calculado corretamente.");
    }

}
