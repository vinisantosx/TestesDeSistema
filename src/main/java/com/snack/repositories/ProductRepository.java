package com.snack.repositories;

import com.snack.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    /**
     * Retorna todos os produtos armazenados.
     */
    public List<Product> getAll() {
        return products;
    }

    /**
     * Retorna o produto com o ID informado, ou null se não existir.
     */
    public Product getById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Verifica se um produto com o ID informado existe.
     */
    public boolean exists(int id) {
        return products.stream().anyMatch(p -> p.getId() == id);
    }

    /**
     * Adiciona um produto ao repositório se o ID ainda não existir.
     */
    public void append(Product product) {
        if (!exists(product.getId())) {
            products.add(product);
        }
    }

    /**
     * Remove o produto com o ID informado, caso exista.
     */
    public void remove(int id) {
        products.removeIf(p -> p.getId() == id);
    }

    /**
     * Atualiza as informações de um produto existente.
     * Se o produto não for encontrado, a operação é ignorada.
     */
    public void update(int id, Product product) {
        Product existing = getById(id);
        if (existing == null) return;

        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setImage(product.getImage());
    }
}
