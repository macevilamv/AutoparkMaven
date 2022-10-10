package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "rents")
@Data
@ToString
@NoArgsConstructor
public class RentEntity {
    @ID
    private Long rentId;
    @Column(name = "vehicleID")
    private Long vehicleID;
    @Column(name = "rentDate")
    private String rentDate;
    @Column(name = "rentCost")
    private Double rentCost;

    public RentEntity(Long vehicleID, String rentDate, double rentCost) {
        this.vehicleID = vehicleID;
        this.rentDate = rentDate;
        this.rentCost = rentCost;
    }
}
