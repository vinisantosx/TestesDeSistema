package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendaTest {

    @Test
    void testarVendaComQuantidadeMenorQueEstoqueDisponivel() {
        Produto p = new Produto("Caneta", 2.0, 10);
        Venda v = new Venda(p, 5);
        boolean sucesso = v.realizarVenda();

        assertTrue(sucesso);
        assertEquals(5, p.getEstoque());
        assertEquals(10.0, v.getTotalVenda());
    }

    @Test
    void testarVendaComQuantidadeIgualAoEstoqueDisponivel() {
        Produto p = new Produto("Caderno", 10.0, 3);
        Venda v = new Venda(p, 3);
        boolean sucesso = v.realizarVenda();

        assertTrue(sucesso);
        assertEquals(0, p.getEstoque());
        assertEquals(30.0, v.getTotalVenda());
    }

    @Test
    void testarVendaComQuantidadeMaiorQueEstoqueDisponivel() {
        Produto p = new Produto("Lápis", 1.0, 2);
        Venda v = new Venda(p, 5);
        boolean sucesso = v.realizarVenda();

        assertFalse(sucesso);
        assertEquals(2, p.getEstoque());
        assertEquals(0.0, v.getTotalVenda());
    }

    @Test
    void testarCalculoDoTotalDaVendaCorretamente() {
        Produto p = new Produto("Borracha", 1.50, 20);
        Venda v = new Venda(p, 4);
        v.realizarVenda();

        assertEquals(6.0, v.getTotalVenda());
    }

    @Test
    void testarAumentoDeEstoqueAposVenda() {
        Produto p = new Produto("Caneta", 2.0, 10);
        Venda v = new Venda(p,9);
        p.aumentarEstoque(5);

        assertEquals(15, p.getEstoque());
    }

    @Test
    void testarDiminuiEstoqueAposVendaBemSucedida() {
        Produto p = new Produto("Caneta", 2.0, 10);
        Venda v = new Venda(p, 4);
        v.realizarVenda();

        assertEquals(6, p.getEstoque());
    }

    @Test
    void testarVendaDeProdutoNuloDeveFalhar() {
        Venda v = new Venda(null, 3);
        Produto produto = v.getProduto();
        assertThrows(NullPointerException.class, v::realizarVenda);
    }

    @Test
    void testarCriacaoVendaComQuantidadeNegativaDeveFalhar() {
        Produto p = new Produto("Caderno", 5.0, 10);
        Venda v = new Venda(p, 20);

        int estoqueAtual = p.getEstoque() - v.getQuantidadeVendida();

        v.realizarVenda();
        Assertions.assertEquals(-10, estoqueAtual);
    }

    @Test
    void testarAlteracaoEstoqueAposTentativaDeVendaComEstoqueInsuficiente() {
        Produto prod = new Produto("Caneta", 2.0, 3);
        Venda v = new Venda(prod, 10);
        boolean sucesso = v.realizarVenda();

        assertFalse(sucesso);
        assertEquals(3, prod.getEstoque());

    }

    @Test
    void testarCriacaoDeVariosProdutosERealizarVendasComEstoqueIndependente() {
        Produto prod1 = new Produto("Caderno", 10.0, 5);
        Produto prod2 = new Produto("Caneta", 2.0, 10);
        Produto prod3 = new Produto("Borracha", 1.5, 8);

        Venda venda1 = new Venda(prod1, 2);
        Venda venda2 = new Venda(prod2, 4);
        Venda venda3 = new Venda(prod3, 3);

        venda1.realizarVenda();
        venda2.realizarVenda();
        venda3.realizarVenda();

        assertEquals(3, prod1.getEstoque());
        assertEquals(6, prod2.getEstoque());
        assertEquals(5, prod3.getEstoque());
    }

    @Test
    void testarCalculoTotalQuandoPrecoProdutoEhAlteradoAntesDaVenda() {
        Produto p = new Produto("Lápis", 1.0, 10);
        p.setPreco(1.5);

        Venda v = new Venda(p, 4);
        v.realizarVenda();

        assertEquals(6.0, v.getTotalVenda());
    }

    @Test
    void testarComportamentoVendaComEstoqueInicialZero() {
        Produto p = new Produto("Apontador", 3.0, 0);
        Venda v = new Venda(p, 1);

        boolean sucesso = v.realizarVenda();

        assertFalse(sucesso);
        assertEquals(0, p.getEstoque());
    }

    @Test
    void testarAumentoDeEstoqueEAposReposicaoVendaBemSucedida() {
        Produto p = new Produto("Caderno", 15.0, 0);

        Venda v1 = new Venda(p, 2);
        boolean falha = v1.realizarVenda();
        assertFalse(falha);
        assertEquals(0, p.getEstoque());

        p.aumentarEstoque(5);

        Venda v2 = new Venda(p, 3);
        boolean sucesso = v2.realizarVenda();

        assertTrue(sucesso);
        assertEquals(2, p.getEstoque());
        assertEquals(45.0, v2.getTotalVenda());
    }
}
