package ru.isakov.market.soap.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isakov.market.models.entities.Category;
import ru.isakov.market.models.repositories.CategoryRepository;
import ru.isakov.market.soap.entitiesWs.categoriesWs.CategoryWs;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryWsService {
    private final CategoryRepository categoryRepository;

    public static final Function<Category, CategoryWs> functionEntityToSoap = category -> {
        CategoryWs categoryWs = new CategoryWs();
        categoryWs.setTitle(category.getTitle());
        category.getProducts().stream().map(ProductWsService.functionEntityToSoap).forEach(p -> categoryWs.getProducts().add(p));
        return categoryWs;
    };

    public CategoryWs getByCategoryTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}
