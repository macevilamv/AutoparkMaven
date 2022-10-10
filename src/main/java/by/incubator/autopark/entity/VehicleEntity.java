package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "vehicles")
@Data
@ToString
@NoArgsConstructor
public class VehicleEntity {
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
    private Integer engineId;

    public VehicleEntity(Long typeId, String model, String registrationNumber, String color, Double weight, Integer mileage, Integer manufactureYear, Integer engineId) {
        this.typeId = typeId;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.weight = weight;
        this.mileage = mileage;
        this.manufactureYear = manufactureYear;
        this.engineId = engineId;
    }
}
