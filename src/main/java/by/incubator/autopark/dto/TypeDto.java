package by.incubator.autopark.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TypeDto {
    private Long id;
    private String name;
    private Double coefTaxes;
}
