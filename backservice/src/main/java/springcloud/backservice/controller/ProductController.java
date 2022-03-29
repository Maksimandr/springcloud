package springcloud.backservice.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springcloud.backservice.converter.ProductMapper;
import springcloud.backservice.dto.ProductDto;
import springcloud.backservice.dto.ProductShortDto;
import springcloud.backservice.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtoList = productService.getAll().stream()
                .map(productMapper::productToProductDto).collect(Collectors.toList());
        return productDtoList;
    }

    @GetMapping("/info/{id}")
    public ProductDto getProductInfo(@PathVariable Long id) {
        ProductDto productDto = productMapper.productToProductDto(productService.findById(id).orElse(null));
        return productDto;
    }

    @PostMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/add")
    @Transactional
    public void saveStudent(@RequestBody ProductShortDto productShortDto) {
        productService.save(productMapper.productShortDtoToProduct(productShortDto));
    }
}
