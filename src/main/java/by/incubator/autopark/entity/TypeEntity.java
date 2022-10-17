package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import by.incubator.autopark.vehicle.TypeInterface;
import lombok.*;

@Table(name = "types")
@Data
@ToString
@NoArgsConstructor
public class TypeEntity implements TypeInterface {
    @ID
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "taxationCoefficient")
    private Double taxationCoefficient;

    public TypeEntity(Long id, String typeName, Double tax) {
        this.id = id;
        this.name = typeName;
        this.taxationCoefficient = tax;
    }

    @Override
    public Double getTaxCoefficient() {
        return taxationCoefficient;
    }

    @Override
    public String getTypeName() {
        return name;
    }
}
