package springcloud.backservice.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import springcloud.backservice.dto.ProductDto;
import springcloud.backservice.dto.ProductShortDto;
import springcloud.backservice.model.Category;
import springcloud.backservice.model.Product;
import springcloud.backservice.repository.CategoryRepository;

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
