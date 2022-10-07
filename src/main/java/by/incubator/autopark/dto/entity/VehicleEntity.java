package by.incubator.autopark.dto.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "vehicles")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {
    @ID
    private Integer id;
    @Column(name = "type_id")
    private Long typeId;
    @Column(name = "color")
    private String color;
    @Column(name = "model")
    private String model;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "weight")
    private double mass;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "manufacture_year")
    private Integer manufactureYear;
    @Column(name = "engine_id")
    private double engineId;
    @Column(name = "rent_list_id")
    private Long rentListId;
}
