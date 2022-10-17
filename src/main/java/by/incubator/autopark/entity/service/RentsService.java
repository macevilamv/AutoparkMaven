package by.incubator.autopark.entity.service;

import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.parsers.csv_parsers.RentsParserFromCsvFile;
import lombok.SneakyThrows;

import java.util.List;

public class RentsService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    RentsParserFromCsvFile parser;
    List<RentEntity> rentEntityList;
    @SneakyThrows
    @InitMethod
    public void init() {
        this.rentEntityList = parser.loadRentEntities();
    }

    public void loadRentEntitiesIntoDatabase() {
        rentEntityList.stream().forEach(this::save);
    }

    public RentEntity get(Long id) {
        return entityManager.get(id, RentEntity.class).get();
    }

    public List<RentEntity> getAll(Class<RentEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public Long save(RentEntity rent) {
        return entityManager.save(rent);
    }

    public void delete(Object obj) {
        entityManager.delete(obj);
    }
}
