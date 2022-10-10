package by.incubator.autopark.entity;

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
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "taxation_Coefficient")
    private Double taxation_Coefficient;
}
