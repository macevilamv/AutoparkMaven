package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "engines")
@Data
@ToString
@NoArgsConstructor
public class EngineEntity {
    @ID
    Long engineId;
    @Column(name = "engineType")
    String engineType;
    @Column(name = "capacity")
    Double capacity;
    @Column(name = "consumption")
    Double consumption;
    @Column(name = "engineVolume")
    Double engineVolume;
    @Column(name = "engineTaxCoeff")
    Double engineTaxCoeff;

    @InitMethod
    public void init(){}

    public EngineEntity(String engineType, Double capacity, Double consumption, Double engineVolume, Double engineTaxCoeff) {
        this.engineType = engineType;
        this.capacity = capacity;
        this.consumption = consumption;
        this.engineVolume = engineVolume;
        this.engineTaxCoeff = engineTaxCoeff;
    }
}
