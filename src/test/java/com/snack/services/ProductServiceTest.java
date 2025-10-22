package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService service;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // Cria um diretório temporário para armazenar as imagens de teste
        tempDir = Files.createTempDirectory("test_images_");

        // Cria uma instância de ProductService redirecionando o caminho para o diretório temporário
        service = new ProductService() {
            {
                this.filePath = tempDir.toString() + File.separator;
            }
        };
    }

    @AfterEach
    void tearDown() throws IOException {
        // Limpa os arquivos e diretórios criados durante os testes
        Files.walk(tempDir)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    // 1️⃣ Salvar um produto com imagem válida
    @Test
    void deveSalvarProdutoComImagemValida() throws IOException {
        // Arrange
        Path image = Files.createTempFile("imagem_valida", ".jpg");
        Product product = new Product(1, "Produto Teste", 10.0F, image.toString());

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertTrue(resultado, "O produto com imagem válida deve ser salvo com sucesso.");
        Path destino = Paths.get(tempDir.toString(), "1.jpg");
        assertTrue(Files.exists(destino), "O arquivo da imagem deve existir no diretório temporário.");
    }

    // 2️⃣ Salvar um produto com imagem inexistente
    @Test
    void deveTratarSalvarProdutoComImagemInexistente() {
        // Arrange
        Product product = new Product(2, "Produto Invalido", 20.0F, "caminho/inexistente.jpg");

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertFalse(resultado, "O método save deve retornar false se a imagem não existir.");
    }

    // 3️⃣ Atualizar um produto existente
    @Test
    void deveAtualizarProdutoExistente() throws IOException {
        // Arrange
        Path image1 = Files.createTempFile("img_original", ".jpg");
        Product produtoOriginal = new Product(3, "Produto Original", 15.0F, image1.toString());
        service.save(produtoOriginal);

        Path image2 = Files.createTempFile("img_nova", ".png");
        Product produtoAtualizado = new Product(3, "Produto Atualizado", 25.0F, image2.toString());

        // Act
        service.update(produtoAtualizado);

        // Assert
        Path novoArquivo = Paths.get(tempDir.toString(), "3.png");
        assertTrue(Files.exists(novoArquivo), "A imagem atualizada deve substituir a anterior.");
        assertEquals(novoArquivo.toString(), produtoAtualizado.getImage());
    }

    // 4️⃣ Remover um produto existente
    @Test
    void deveRemoverProdutoExistente() throws IOException {
        // Arrange
        Path image = Files.createTempFile("img_remover", ".jpg");
        Product product = new Product(4, "Produto Remover", 30.0F, image.toString());
        service.save(product);

        Path destino = Paths.get(tempDir.toString(), "4.jpg");
        assertTrue(Files.exists(destino), "A imagem deve existir antes da remoção.");

        // Act
        service.remove(4);

        // Assert
        assertFalse(Files.exists(destino), "A imagem deve ser removida do diretório.");
    }

    // 5️⃣ Obter caminho da imagem por ID
    @Test
    void deveObterCaminhoDaImagemPorId() throws IOException {
        // Arrange
        Path image = Files.createTempFile("img_busca", ".jpg");
        Product product = new Product(5, "Produto Busca", 50.0F, image.toString());
        service.save(product);

        // Act
        String caminho = service.getImagePathById(5);

        // Assert
        assertNotNull(caminho, "O caminho da imagem não deve ser nulo.");
        assertTrue(caminho.endsWith("5.jpg"), "O caminho deve terminar com o nome do arquivo esperado (ex: 5.jpg).");
    }
}
