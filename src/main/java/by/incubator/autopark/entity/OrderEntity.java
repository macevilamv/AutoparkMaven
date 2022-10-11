package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "orders")
@Data
@ToString
@NoArgsConstructor
public class OrderEntity {
    @ID
    Long vehicleId;
    @Column(name = "breakings")
    String breakings;

    @InitMethod
    public void init(){}
    public OrderEntity(Long vehicleId, String orders) {
        this.vehicleId = vehicleId;
        this.breakings = orders;
    }
}
