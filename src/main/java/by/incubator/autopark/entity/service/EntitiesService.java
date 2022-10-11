package by.incubator.autopark.entity.service;

import by.incubator.autopark.entity.EngineEntity;
import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.entity.TypeEntity;
import by.incubator.autopark.infrastructure.core.FactoryService;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.infrastructure.orm.impl.EntityManagerImpl;

import java.util.List;

public class EntitiesService {
    private static FactoryService service = new FactoryService();
    private static EntityManager entityManager = service.getObjectFactory().createObject(EntityManagerImpl.class);

    public static List<RentEntity> getRents(Class<RentEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public static RentEntity getRent(Long id) {
        return entityManager.get(id, RentEntity.class).get();
    }

    public static EngineEntity getEngine(Long id) {
        return entityManager.get(id, EngineEntity.class).get();
    }

    public static List<EngineEntity> getEngines(Class<EngineEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public static TypeEntity getType(Long id) {
        return entityManager.get(id,TypeEntity.class).get();
    }

    public static List<TypeEntity> getTypes(Class<TypeEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public static void delete(Object obj) {
        entityManager.delete(obj);
    }
}
