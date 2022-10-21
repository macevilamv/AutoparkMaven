package by.incubator.autopark.vehicle;

import by.incubator.autopark.engine.Startable;

public interface Driveable {
    Long getId();
    Long getTypeId();
    TypeInterface getType();
    String getTypeName();
    String getModel();
    String getRegistrationNumber();
    Double getWeight();
    String getColor();
    Integer getManufactureYear();
    Integer getMileage();
    Double getTotalIncome();
    Double getCalcTaxPerMonth();
    Double getTotalProfit();
    Startable getEngine();
}
