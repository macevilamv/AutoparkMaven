package by.incubator.autopark.dto.entity.service;

import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;

public class EngineService {
    EntityManager entityManager;

    @InitMethod
    public void init() {

    }
}
