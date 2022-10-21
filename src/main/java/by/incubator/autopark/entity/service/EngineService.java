package by.incubator.autopark.entity.service;

import by.incubator.autopark.entity.EngineEntity;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.parsers.csv_parsers.EngineParser;

import java.util.List;

public class EngineService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    EngineParser parser;
    List<EngineEntity> engineEntities;
    @InitMethod
    public void init() {
    }

    public void loadEngineEntitiesFromCsvIntoDatabase() {
        engineEntities = parser.loadEngineEntities();
        engineEntities.stream().forEach(this::save);
    }

    public EngineEntity get(Long id) {
        return entityManager.get(id, EngineEntity.class).get();
    }

    public List<EngineEntity> getAll(Class<EngineEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public Long save(EngineEntity type) {
        return entityManager.save(type);
    }

    public void delete(Object obj) {
        entityManager.delete(obj);
    }
}
