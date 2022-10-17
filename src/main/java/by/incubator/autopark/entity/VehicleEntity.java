package by.incubator.autopark.entity;

import by.incubator.autopark.entity.service.*;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import by.incubator.autopark.vehicle.Driveable;
import lombok.*;

import java.util.List;

@Table(name = "vehicles")
@ToString
@NoArgsConstructor
public class VehicleEntity implements Driveable {
    @ID
    private Long id;
    @Column(name = "type")
    private Long type;
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
    @Column(name = "engine")
    private Long engine;
    @Column(name = "totalIncome")
    private Double totalIncome = 0.0d;

    public void setFields(Long type, String model, String registrationNumber, String color, Double weight, Integer mileage, Integer manufactureYear, Long engine) {
        this.type = type;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.weight = weight;
        this.mileage = mileage;
        this.manufactureYear = manufactureYear;
        this.engine = engine;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
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

    public Long getEngine() {
        return engine;
    }

    public void setEngine(Long engine) {
        this.engine = engine;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String getTypeName() {
        return EntitiesService.getType(type).getName();
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
                (this.weight * 0.0013) + (EntitiesService.getType(type).getTaxationCoefficient()
                        * EntitiesService.getEngine(engine).getEngineTaxCoeff() * 30) + 5));
    }
}
