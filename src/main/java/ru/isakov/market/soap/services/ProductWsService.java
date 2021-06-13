package ru.isakov.market.soap.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isakov.market.models.entities.Product;
import ru.isakov.market.models.repositories.ProductRepository;
import ru.isakov.market.soap.entitiesWs.productsWs.ProductWs;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductWsService {
    private final ProductRepository productRepository;

    public static final Function<Product, ProductWs> functionEntityToSoap = product -> {
        ProductWs productWs = new ProductWs();
        productWs.setId(product.getId());
        productWs.setTitle(product.getTitle());
        productWs.setPrice(product.getPrice());
        return productWs;
    };

    public List<ProductWs> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductWs getProductByTitle(String title) {
        return productRepository.findByTitle(title).map(functionEntityToSoap).get();
    }

    public ProductWs getProductById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }
}
