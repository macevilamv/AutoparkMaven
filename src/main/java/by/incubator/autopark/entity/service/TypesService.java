package by.incubator.autopark.entity.service;

import by.incubator.autopark.entity.TypeEntity;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.parsers.csv_parsers.VehicleTypeParserFromCsvFile;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

public class TypesService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    VehicleTypeParserFromCsvFile parser;
    List<TypeEntity> typeEntityList;
    @SneakyThrows
    @InitMethod
    public void init() {
    }

    public void loadTypeEntitiesFromCsvIntoDatabase() throws IOException {
        this.typeEntityList = parser.loadTypeEntities();
        typeEntityList.stream().forEach(this::save);
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

    public void delete(Object obj) {
        entityManager.delete(obj);
    }
}
