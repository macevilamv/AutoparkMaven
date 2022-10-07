package by.incubator.autopark.dto.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "rents")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentEntity {
    private int vehicleId;
    private String rentDate;
    private double rentCost;
}
