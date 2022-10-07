package by.incubator.autopark.dto.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "types")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TypeEntity {
    @ID
    private long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "coefTaxes")
    private Double coefTaxes;
}
