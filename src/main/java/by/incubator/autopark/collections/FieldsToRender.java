package by.incubator.autopark.collections;

import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.vehicle.CarColor;
import by.incubator.autopark.vehicle.VehicleType;

public enum FieldsToRender {
    id, Type, Model, Number, Color, Weight, Mileage, Tax, Year, Income, Profit;
}
