package by.incubator.autopark.entity.service;

import by.incubator.autopark.entity.OrderEntity;
import by.incubator.autopark.entity.OrderEntity;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.parsers.csv_parsers.BreakingParserFromCsvFile;
import lombok.SneakyThrows;

import java.util.List;

public class OrdersService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    BreakingParserFromCsvFile parser;
    List<OrderEntity> orderEntityList;
    @SneakyThrows
    @InitMethod
    public void init() {
    }

    public void loadOrderEntitiesFromCsvIntoDatabase() {
        this.orderEntityList = parser.loadOrderEntities();
        orderEntityList.stream().forEach(this::save);
    }

    public OrderEntity get(Long id) {
        return entityManager.get(id, OrderEntity.class).get();
    }

    public List<OrderEntity> getAll(Class<OrderEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public Long save(OrderEntity order) {
        return entityManager.save(order);
    }

    public void delete(Object obj) {
        entityManager.delete(obj);
    }
}
