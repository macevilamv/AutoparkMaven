package by.incubator.autopark.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "engines")
@Data
@ToString
@NoArgsConstructor
public class EngineEntity {
    @ID
    Integer engineId;
    @Column(name = "engineType")
    String engineType;
    @Column(name = "capacity")
    Double capacity;
    @Column(name = "consumption")
    Double consumption;
    @Column(name = "engineVolume")
    Double engineVolume;

    public EngineEntity(String engineType, Double capacity, Double consumption, Double engineVolume) {
        this.engineType = engineType;
        this.capacity = capacity;
        this.consumption = consumption;
        this.engineVolume = engineVolume;
    }
}
