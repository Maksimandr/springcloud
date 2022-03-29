package springcloud.backservice.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String price;
    private String category;
}
