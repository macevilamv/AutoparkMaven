package by.incubator.autopark.collections;

import by.incubator.autopark.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleTableRenderer {
    static void renderVehicleTable(List<Vehicle> vehicles) {
        Map<String, Integer> columnsLengths = new HashMap<>();
        FieldsToRender[] fields = FieldsToRender.values();
        String pattern;

        getColumnsLengthData(columnsLengths, fields, vehicles);

        pattern = "| %" + columnsLengths.get(FieldsToRender.ID.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Type.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Model.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Number.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Weight.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Year.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Mileage.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Color.name()) + "s | "
                + " %" + columnsLengths.get(FieldsToRender.Income.name()) + "s |"
                + " %" + columnsLengths.get(FieldsToRender.Tax.name()) + "s | "
                + " %" + columnsLengths.get(FieldsToRender.Profit.name()) + "s | \n";

        System.out.printf(pattern, FieldsToRender.ID.name(), FieldsToRender.Type.name(),
                FieldsToRender.Model.name(), FieldsToRender.Number.name(),
                FieldsToRender.Weight.name(), FieldsToRender.Year.name(),
                FieldsToRender.Mileage.name(), FieldsToRender.Color.name(),
                FieldsToRender.Income.name(), FieldsToRender.Tax.name(),
                FieldsToRender.Profit.name());

        for (Vehicle vehicle : vehicles) {
            System.out.printf(pattern,
                    vehicle.getId(), vehicle.getType().getTypeName(), vehicle.getModel(),
                    vehicle.getRegistrationNumber(), vehicle.getMass(), vehicle.getManufactureYear(),
                    vehicle.getMileage(), vehicle.getColor(), vehicle.getTotalIncome(), vehicle.getCalcTaxPerMonth(),
                    vehicle.getTotalProfit()
            );
        }
    }

    private static void getColumnsLengthData(Map<String, Integer> columnsLengths, FieldsToRender[] fields,
                                      List<Vehicle> vehicles) {
        for (FieldsToRender field : fields) {
            columnsLengths.put(field.name(), field.name().length());
        }

        for (Vehicle vehicle : vehicles) {
            columnsLengths.computeIfPresent(FieldsToRender.ID.name(),
                    (key, oldValue) -> Integer.max(oldValue, vehicle.getId()));
            columnsLengths.computeIfPresent(FieldsToRender.Type.name(),
                    (key, oldValue) -> Integer.max(oldValue, vehicle.getType().getTypeName().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Model.name(),
                    (key, oldValue) -> Integer.max(oldValue, vehicle.getModel().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Color.name(),
                    (key, oldValue) -> Integer.max(oldValue, vehicle.getColor().name().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Profit.name(),
                    (key, oldValue) -> Integer.max(oldValue, ((Object) vehicle.getTotalProfit()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Weight.name(),
                    (key, oldValue) -> Integer.max(oldValue, ((Object) vehicle.getMass()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Mileage.name(),
                    (key, oldValue) -> Integer.max(oldValue, ((Object) vehicle.getMileage()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Number.name(),
                    (key, oldValue) -> Integer.max(oldValue, vehicle.getRegistrationNumber().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Income.name(),
                    (key, oldValue) -> Integer.max(oldValue, ((Object) vehicle.getTotalIncome()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Year.name(),
                    (key, oldValue) -> Integer.max(oldValue, ((Object) vehicle.getManufactureYear()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Tax.name(),
                    (key, oldValue) -> Integer.max(oldValue, ((Object) vehicle.getCalcTaxPerMonth()).toString().length()));
        }
    }
}

