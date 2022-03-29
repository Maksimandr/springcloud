package springcloud.backservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductShortDto {

  private String title;
  private Float price;
  private String category;
}
