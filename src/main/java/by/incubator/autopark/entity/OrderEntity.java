package by.incubator.autopark.entity;

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
    Long orderId;
    @Column(name = "vehicleId")
    Long vehicleId;
    @Column(name = "orders")
    String orders;

    public OrderEntity(Long vehicleId, String orders) {
        this.vehicleId = vehicleId;
        this.orders = orders;
    }
}
