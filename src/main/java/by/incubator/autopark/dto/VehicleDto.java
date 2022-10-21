package by.incubator.autopark.dto;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VehicleDto {
    private Long id;
    private Long type;
    private String typeName;
    private String model;
    private Double coefTaxes;
    private String registrationNumber;
    private String color;
    private Double weight;
    private Integer mileage;
    private Integer manufactureYear;
    private String engineName;
    private Double tankCapacity;
    private Double engineVolume;
    private Double totalIncome;
    private Double per100Kilometers;
    private Double maxKilometers;
    private Double tax;
    private Double income;
    private Double profit;
}
