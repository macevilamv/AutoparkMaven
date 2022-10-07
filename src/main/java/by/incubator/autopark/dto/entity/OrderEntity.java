package by.incubator.autopark.dto.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "orders")
@Builder
@Data
@ToString
@NoArgsConstructor
public class OrderEntity {
}
