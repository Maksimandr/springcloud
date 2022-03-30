package ru.gb.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.dto.ProductDto;
import ru.gb.model.Category;
import ru.gb.model.Product;
import ru.gb.repository.CategoryRepository;
import ru.gb.dto.ProductShortDto;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Mapping(source = "category", target = "category", qualifiedByName = "titleToCategory")
    public abstract Product productShortDtoToProduct(ProductShortDto productShortDto);

    @Mapping(source = "category.title", target = "category")
    public abstract ProductDto productToProductDto(Product product);

    @Named("titleToCategory")
    public Category categoryTitleToCategory(String categoryTitle) {
        return categoryRepository.findByTitle(categoryTitle).orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
    }
}
