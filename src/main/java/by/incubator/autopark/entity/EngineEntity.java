package by.incubator.autopark.entity;

import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "engines")
@Data
@ToString
@NoArgsConstructor
public class EngineEntity implements Startable {
    @ID
    Long engineId;
    @Column(name = "engineName")
    String engineName;
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

    public EngineEntity(String engineName, Double capacity, Double consumption, Double engineVolume, Double engineTaxCoeff) {
        this.engineName = engineName;
        this.capacity = capacity;
        this.consumption = consumption;
        this.engineVolume = engineVolume;
        this.engineTaxCoeff = engineTaxCoeff;
    }

    public double getCapacity() {
        return capacity;
    }
    public double getConsumption() {
        return this.consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    @Override
    public String getName() {
        return this.engineName;
    }

    @Override
    public double getTaxPerMonth() {
        return this.engineTaxCoeff;
    }

    @Override
    public double getMaxKilometers() {
        return (this.capacity / this.consumption) * 100;
    }

    @Override
    public double getVolume() {
        return this.engineVolume;
    }
}
