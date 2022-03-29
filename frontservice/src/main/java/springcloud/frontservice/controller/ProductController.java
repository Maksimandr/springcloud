package springcloud.frontservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import springcloud.frontservice.dto.ProductDto;
import springcloud.frontservice.dto.ProductShortDto;

import java.util.List;

@Controller
public class ProductController {

    private RestTemplate restTemplate;

    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductDto> productDtoList = restTemplate.getForObject("http://eureka-back-service", List.class);
        model.addAttribute("products", productDtoList);
        return "product_list";
    }

    @GetMapping("/info/{id}")
    public String getProductInfo(@PathVariable Long id, Model model) {
        ProductDto productDto = restTemplate.getForObject("http://eureka-back-service/info/" + id, ProductDto.class);
        model.addAttribute("product", productDto);
        return "product_info";
    }

    @PostMapping("/delete/{id}")
    public String getProductInfo(@PathVariable Long id) {
        restTemplate.postForObject("http://eureka-back-service/delete/" + id, id, Long.class);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String ProductAddFrom(Model model) {
        model.addAttribute("productShortDto", new ProductShortDto());
        return "add_product_form";
    }

    @PostMapping("/add")
    public String ProductAddFrom(ProductShortDto productShortDto) {
        restTemplate.postForObject("http://eureka-back-service/add/", productShortDto, ProductShortDto.class);
        return "redirect:/";
    }
}
