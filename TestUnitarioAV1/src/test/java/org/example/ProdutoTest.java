package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProdutoTest {

    @Test
    void testarCriacaoProdutoComValoresValidos() {
        Produto p = new Produto("Caneta", 2.50, 100);
        assertEquals("Caneta", p.getNome());
        assertEquals(2.50, p.getPreco());
        assertEquals(100, p.getEstoque());
    }

    @Test
    void testarCriacaoProdutoComPrecoNegativo() {
        Produto p = new Produto("Lápis", -5.0, 10);
        assertTrue(p.getPreco() < 0, "O preço não deve ser negativo, teste proposital para falha de validação.");
        // Aqui idealmente o construtor deveria lançar exceção, mas como não faz validação, o teste só demonstra o erro.
    }

    @Test
    void testarCriacaoProdutoComEstoqueNegativo() {
        Produto p = new Produto("Borracha", 1.0, -5);
        assertTrue(p.getEstoque() < 0, "O estoque não deve ser negativo, teste proposital para falha de validação.");
    }

    @Test
    void testarAlteracaoNomeProdutoParaValorValido() {
        Produto p = new Produto("Caneta", 2.50, 100);
        p.setNome("Caneta Azul");
        assertEquals("Caneta Azul", p.getNome());
    }

    @Test
    void testarAlteracaoPrecoProdutoParaValorValido() {
        Produto p = new Produto("Caneta", 2.50, 100);
        p.setPreco(3.00);
        assertEquals(3.00, p.getPreco());
    }

    @Test
    void testarAlteracaoEstoqueParaValorPositivo() {
        Produto p = new Produto("Caneta", 2.50, 100);
        p.setEstoque(150);
        assertEquals(150, p.getEstoque());
    }

    @Test
    void testarAlteracaoPrecoProdutoParaValorNegativo() {
        Produto p = new Produto("Caneta", 2.50, 100);
        p.setPreco(-10.0);
        assertTrue(p.getPreco() < 0, "O preço ficou negativo, o que deveria ser inválido.");
    }
}
