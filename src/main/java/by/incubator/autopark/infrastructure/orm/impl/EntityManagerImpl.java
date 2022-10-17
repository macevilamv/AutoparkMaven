package by.incubator.autopark.infrastructure.orm.impl;

import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.orm.ConnectionFactory;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.infrastructure.orm.service.PostgreDataBase;

import java.util.List;
import java.util.Optional;

public class EntityManagerImpl implements EntityManager {
    @Autowired
    private ConnectionFactory connection;
    @Autowired
    private PostgreDataBase dataBaseService;
    @Autowired
    Context context;

    public EntityManagerImpl() {
    }

    @Override
    public <T> Optional<T> get(Long id, Class<T> clazz) {
        return dataBaseService.get(id, clazz);
    }

    @Override
    public Long save(Object object) {
        return dataBaseService.save(object);
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return dataBaseService.getAll(clazz);
    }

    @Override
    public void delete(Object obj) {
        dataBaseService.delete(obj);
    }
}
