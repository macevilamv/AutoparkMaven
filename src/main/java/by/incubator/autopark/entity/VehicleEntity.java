package by.incubator.autopark.entity;

import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.entity.service.*;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.TypeInterface;
import lombok.*;

import java.util.List;

@Table(name = "vehicles")
@ToString
@NoArgsConstructor
public class VehicleEntity implements Driveable {
    @ID
    private Long id;
    @Column(name = "typeId")
    private Long typeId;
    @Column(name = "model")
    private String model;
    @Column(name = "registrationNumber")
    private String registrationNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "manufactureYear")
    private Integer manufactureYear;
    @Column(name = "engineId")
    private Long engineId;
    @Column(name = "totalIncome")
    private Double totalIncome = 0.0d;

    public void setFields(Long type, String model, String registrationNumber, String color, Double weight, Integer mileage, Integer manufactureYear, Long engine) {
        this.typeId = type;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.weight = weight;
        this.mileage = mileage;
        this.manufactureYear = manufactureYear;
        this.engineId = engine;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getTypeId() {
        return typeId;
    }

    @Override
    public Startable getEngine() {
        return (Startable) EntitiesService.getEngine(this.engineId);
    }

    public TypeInterface getType() {
        return EntitiesService.getType(this.typeId);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String getTypeName() {
        return EntitiesService.getType(typeId).getName();
    }

    @Override
    public Double getTotalIncome() {
        Double sum = 0.0d;
        List<RentEntity> rentEntities = EntitiesService.getRents(RentEntity.class);
        for (RentEntity entity : rentEntities) {
            if (Long.compare(this.id, entity.getVehicleId()) == 0) {
                sum += entity.getRentCost();
            }
        }

        return sum;
    }

    @Override
    public Double getTotalProfit() {
        return Double.parseDouble(String.format("%.2f",getTotalIncome() - getCalcTaxPerMonth()));
    }

    @Override
    public Double getCalcTaxPerMonth() {

        return Double.parseDouble(String.format("%.2f",
                (this.weight * 0.0013) + (EntitiesService.getType(typeId).getTaxationCoefficient()
                        * EntitiesService.getEngine(engineId).getEngineTaxCoeff() * 30) + 5));
    }
}
