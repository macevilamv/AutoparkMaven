package by.incubator.autopark.dto.entity;

import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;

public class EngineEntity {
    @ID
    Long engineId;
    @Column(name = "engine_type")
    String engineType;
}
