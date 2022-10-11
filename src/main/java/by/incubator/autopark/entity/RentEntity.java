package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import by.incubator.autopark.rent.Rentable;
import lombok.*;

@Table(name = "rents")
@Data
@ToString
@NoArgsConstructor
public class RentEntity implements Rentable {
    @ID
    private Long rentId;
    @Column(name = "vehicleId")
    private Long vehicleId;
    @Column(name = "rentDate")
    private String rentDate;
    @Column(name = "rentCost")
    private Double rentCost;

    public RentEntity(Long vehicleId, String rentDate, Double cost) {
        this.vehicleId = vehicleId;
        this.rentDate = rentDate;
        this.rentCost = cost;
    }

    @Override
    public Long getVehicleId() {
        return vehicleId;
    }
}
