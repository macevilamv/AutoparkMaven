package by.incubator.autopark.dto.entity.service;

import by.incubator.autopark.dto.entity.TypeEntity;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.vehicle.VehicleType;
import lombok.SneakyThrows;

import java.util.List;

public class TypesService {
    @Autowired
    EntityManager entityManager;

    @SneakyThrows
    @InitMethod
    public void init() {

    }

    public TypeEntity get(Long id) {
       return entityManager.get(id,TypeEntity.class).get();
    }

    public List<TypeEntity> getAll(Class<TypeEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public Long save(TypeEntity type) {
        return entityManager.save(type);
    }
}
