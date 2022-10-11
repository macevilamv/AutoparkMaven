package by.incubator.autopark.infrastructure.orm.service;

import by.incubator.autopark.exceptions.IsNotAnnotatedByTableException;
import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.FactoryService;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.ConnectionFactory;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import by.incubator.autopark.infrastructure.orm.enums.SqlFieldType;
import by.incubator.autopark.utils.StringProcessor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class PostgreDataBase {
    private static final String CHECK_SEQ_SQL_PATTERN =
            "SELECT EXISTS (\n"
                    + "SELECT FROM information_schema.sequences \n"
                    + "WHERE sequence_schema = 'public'\n"
                    + "AND sequence_name = '%s'\n"
                    + ");";
    private static final String CREATE_ID_SEQ_PATTERN =
            "CREATE SEQUENCE %S\n"
                    + "INCREMENT 1\n"
                    + "START 1;";
    private static final String CHECK_TABLE_SQL_PATTERN =
            "SELECT EXISTS (\n"
                    + "SELECT FROM information_schema.tables\n"
                    + "WHERE table_schema = 'public'\n"
                    + "AND table_name  = '%s'\n" + ");";
    private static final String CREATE_TABLE_SQL_PATTERN =
            "CREATE TABLE %s (\n"
                    + " %s SERIAL PRIMARY KEY, "
                    + "%S\n);";
    private static final String INSERT_SQL_PATTERN =
            "INSERT INTO %s(%s)\n"
                    + "VALUES (%s)\n"
                    + "RETURNING %s;";
    private static final String DELETE_SQL_PATTERN =
            "DELETE FROM %s\n" +
                    "WHERE %s = %s;";
    @Autowired
    private ConnectionFactory factory;
    private Map<String, String> classToSql;
    private Map<String, String> insertPatternByClass;
    private Map<String, String> insertByClassPattern;
    private Map<String, String> deleteByClassPattern = new HashMap<>();
    @Autowired
    private Context context;

    @InitMethod
    public void init() {
        initializeClassToSql();
        initializeInsertPatternByClass();
        initializeInsertByClassPattern();
        validateEntitiesTablesToExist();
        initializeDeleteByClassPattern();

        initializeClassToSql();
    }

    public void delete(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Long id = null;
        String fieldName = "id";

        for (Field field :
                fields) {
            if (field.isAnnotationPresent(ID.class)) {
                Method method = null;
                try {
                    fieldName = field.getName();
                    method = obj.getClass().getMethod(StringProcessor.generateGetterName(field));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    id = (Long) method.invoke(obj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        String sql = String.format(deleteByClassPattern.get(obj.getClass().getName()), fieldName, id);

        try (Connection connection = factory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public Long save(Object obj) {
        Object[] values = getValuesLine(obj);
        String nameId = findIdField(obj.getClass());

        String sql = String.format(insertByClassPattern.get(obj.getClass().getName()), values);

        try (Connection connection = factory.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return resultSet.getLong(nameId);
            } else {
                throw new RuntimeException("There is no id field");
            }
        }
    }
    @SneakyThrows
    public <T> List<T> getAll(Class<T> clazz) {
        List<T> tObjectList = new ArrayList<>();

        if (clazz.isAnnotationPresent(Table.class)) {
            String tableName = clazz.getAnnotation(Table.class).name();
            String sql = "SELECT * FROM " + tableName;

            try (Connection connection = factory.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    tObjectList.add(createObject(resultSet, clazz));
                }
            }
        } else {
            throw new IsNotAnnotatedByTableException();
        }
        return tObjectList;
    }

    @SneakyThrows
    public <T> Optional<T> get(Long id, Class<T> tClass) {
        Optional<T> tOptional;
        if (tClass.isAnnotationPresent(Table.class)) {
            String tableName = tClass.getAnnotation(Table.class).name();
            String idFieldName = findIdField(tClass);
            String sql = "SELECT * FROM " + tableName + " WHERE " + idFieldName
                    + " = " + id + ";";

            try (Connection connection = factory.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.next();
                tOptional = Optional.of(createObject(resultSet, tClass));
            }
        } else {
            throw new IsNotAnnotatedByTableException();
        }
        return tOptional;
    }

    @SneakyThrows
    public <T> T createObject(ResultSet resultSet, Class<T> clazz) {
        FactoryService service = new FactoryService();
        ObjectFactory factory = service.getObjectFactory();
        T object = clazz.getConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(ID.class)) {
                String setterName = StringProcessor.generateSetterName(field);
                Method setterMethod = object.getClass().getMethod(setterName, field.getType());
                invokeSetterMethodByFieldType(setterMethod, field, object, resultSet);
            }
        }
        return object;
    }

    @SneakyThrows
    private Object[] getValuesLine(Object obj) {
        Object[] objects = new Object[obj.getClass().getDeclaredFields().length];
        int counter = 0;

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Method method = obj.getClass().getMethod(StringProcessor.generateGetterName(field));
                objects[counter++] = method.invoke(obj);
            }
        }
        return objects;
    }

    private void initializeClassToSql() {
        this.classToSql = Arrays.stream(SqlFieldType.values())
                .collect(Collectors
                        .toMap(element -> element.getType().getName(), SqlFieldType::getSqlType));
    }

    private void initializeInsertPatternByClass() {
        this.insertPatternByClass = Arrays.stream(SqlFieldType.values())
                .collect(Collectors
                        .toMap(element -> element.getType().getName(), SqlFieldType::getInsertPattern));
    }

    public void initializeInsertByClassPattern() {
        this.insertByClassPattern = getTableSet()
                .stream()
                .collect(Collectors.toMap(key -> key.getName(),
                        value -> {
                            String tableName = value.getAnnotation(Table.class).name();
                            return makeInsertSqlQuery(value, tableName);
                        }));
    }

    @SneakyThrows
    private void invokeSetterMethodByFieldType(Method setterMethod, Field field, Object object, ResultSet resultSet) {
        if (field.getType() == String.class) {
            setterMethod.invoke(object, resultSet.getString(field.getName()));
        }

        if (field.getType() == Integer.class) {
            setterMethod.invoke(object, resultSet.getInt(field.getName()));
        }

        if (field.getType() == Long.class) {
            setterMethod.invoke(object, resultSet.getLong(field.getName()));
        }

        if (field.getType() == Double.class) {
            setterMethod.invoke(object, resultSet.getDouble(field.getName()));
        }

        if (field.getType() == Date.class) {
            setterMethod.invoke(object, resultSet.getDate(field.getName()));
        }
    }

    private Set<Class<?>> getTableSet() {
        return context.getConfig().getScanner().getReflections().getTypesAnnotatedWith(Table.class);
    }

    @SneakyThrows
    public void validateEntitiesTablesToExist() {
        String sql;
        String tableName;

        for (Class<?> clazz : getTableSet()) {
            tableName = clazz.getAnnotation(Table.class).name();
            sql = String.format(CHECK_TABLE_SQL_PATTERN, tableName);

            try (Connection connection = factory.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    if (!resultSet.getBoolean("exists")) {
                        statement.execute(makeCreateSqlQuery(clazz, tableName, makeFieldsForCreateSqlQuery(clazz)));
                    }
                }
            }
        }
    }

    public String makeInsertSqlQuery(Class<?> type, String tableName) {
        String idField = findIdField(type);
        String fields = makeFieldsForInsertQuery(type);
        String values = makeValuesForInsertQuery(type);

        return String.format(INSERT_SQL_PATTERN, tableName, fields, values, idField);
    }

    public String makeCreateSqlQuery(Class<?> type, String tableName, String fields) {
        String idField = findIdField(type);
        String createQuery = String.format(CREATE_TABLE_SQL_PATTERN, tableName, idField, fields);

        return createQuery;
    }

    public String makeFieldsForCreateSqlQuery(Class<?> type) {
        StringBuilder fields = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                fields.append(field.getAnnotation(Column.class).name());
                fields.append(" " + classToSql.get(field.getType().getName()));
                fields.append(", ");
            }
        }
        fields.delete(fields.lastIndexOf(","), fields.lastIndexOf(",") + 2);

        return fields.toString();
    }

    public String makeFieldsForInsertQuery(Class<?> type) {
        StringBuilder insertedFields = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                insertedFields.append(field.getName());
                insertedFields.append(", ");
            }
        }
        insertedFields.delete(insertedFields.lastIndexOf(","), insertedFields.lastIndexOf(",") + 2);

        return insertedFields.toString();
    }

    public String makeValuesForInsertQuery(Class<?> type) {
        StringBuilder values = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                values.append(insertPatternByClass.get(field.getType().getName()));
                values.append(", ");
            }
        }
        values.delete(values.lastIndexOf(","), values.lastIndexOf(",") + 2);

        return values.toString();
    }

    private String findIdField(Class<?> clazz) {
        String id = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) {
                id = field.getName();
                return id;
            }
        }
        return id;
    }

    private void initializeDeleteByClassPattern() {
        Set<Class<?>> entities =
                context.getConfig().getScanner().getReflections().getTypesAnnotatedWith(Table.class);
        entities.stream().forEach(x -> putDeletePatternToMap(x));
    }

    private void putDeletePatternToMap(Class<?> clazz) {
        String tableName = clazz.getAnnotation(Table.class).name();

        String sql = String.format(DELETE_SQL_PATTERN, tableName, "%s", "%s");

        deleteByClassPattern.put(clazz.getName(), sql);
    }

}

