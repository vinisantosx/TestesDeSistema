package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();

        product1 = new Product(1, "Refrigerante", 5.0F, "image1.png");
        product2 = new Product(2, "Salgadinho", 2.5F, "image2.png");
    }

    @Test
    @DisplayName("1️⃣ Fazer adição correta de um produto ao repositório")
    void fazerAdicaoCorretaDeProdutoAoRepositorio() {
        repository.append(product1);
        assertEquals(1, repository.getAll().size());
        assertTrue(repository.getAll().contains(product1));
    }

    @Test
    @DisplayName("2️⃣ Testar recuperação de um produto pelo seu ID")
    void testarRecuperacaoDeProdutoPorId() {
        repository.append(product1);
        Product encontrado = repository.getById(1);
        assertEquals(product1, encontrado);
    }

    @Test
    @DisplayName("3️⃣ Validar se um produto existe no repositório")
    void validarExistenciaDeProdutoNoRepositorio() {
        repository.append(product1);
        assertTrue(repository.exists(1));
        assertFalse(repository.exists(999));
    }

    @Test
    @DisplayName("4️⃣ Testar remoção correta de um produto do repositório")
    void testarRemocaoCorretaDeProdutoDoRepositorio() {
        repository.append(product1);
        repository.remove(1);
        assertFalse(repository.exists(1));
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    @DisplayName("5️⃣ Validar atualização correta de um produto existente")
    void validarAtualizacaoCorretaDeProduto() {
        repository.append(product1);
        Product atualizado = new Product(1, "Refrigerante Zero", 6.0F, "image_new.png");

        repository.update(1, atualizado);

        Product result = repository.getById(1);
        assertEquals("Refrigerante Zero", result.getDescription());
        assertEquals(6.0, result.getPrice());
        assertEquals("image_new.png", result.getImage());
    }

    @Test
    @DisplayName("6️⃣ Testar recuperação de todos os produtos armazenados")
    void testarRecuperacaoDeTodosOsProdutosArmazenados() {
        repository.append(product1);
        repository.append(product2);
        List<Product> todos = repository.getAll();

        assertEquals(2, todos.size());
        assertTrue(todos.contains(product1));
        assertTrue(todos.contains(product2));
    }

    @Test
    @DisplayName("7️⃣ Tratar remoção de produto inexistente sem lançar erro")
    void tratarRemocaoDeProdutoInexistenteSemErro() {
        repository.append(product1);
        assertDoesNotThrow(() -> repository.remove(999));
        assertEquals(1, repository.getAll().size());
    }

    @Test
    @DisplayName("8️⃣ Testar tentativa de atualização de produto não existente")
    void testarAtualizacaoDeProdutoNaoExistente() {
        Product inexistente = new Product(99, "Inexistente", 1.0F, "none.png");
        assertThrows(Exception.class, () -> repository.update(99, inexistente));
    }

    @Test
    @DisplayName("9️⃣ Validar rejeição de adição de produtos com IDs duplicados")
    void validarRejeicaoDeProdutosComIdsDuplicados() {
        repository.append(product1);
        Product duplicado = new Product(1, "Duplicado", 4.0F, "dup.png");
        repository.append(duplicado);

        // Espera-se apenas um produto com o mesmo ID
        long count = repository.getAll().stream().filter(p -> p.getId() == 1).count();
        assertEquals(1, count);
    }

    @Test
    @DisplayName("🔟 Confirmar que o repositório inicia vazio")
    void confirmarQueRepositorioIniciaVazio() {
        assertTrue(repository.getAll().isEmpty());
    }
}
