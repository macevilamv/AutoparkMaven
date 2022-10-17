package by.incubator.autopark.collections;

import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleTableRenderer {
    static void renderVehicleTable(List<Driveable> vehicles) {
        Map<String, Long> columnsLengths = new HashMap<>();
        FieldsToRender[] fields = FieldsToRender.values();
        String pattern;

        getColumnsLengthData(columnsLengths, fields, vehicles);

        pattern = "| %" + columnsLengths.get(FieldsToRender.id.name()) + "s |"
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

        System.out.printf(pattern, FieldsToRender.id.name(), FieldsToRender.Type.name(),
                FieldsToRender.Model.name(), FieldsToRender.Number.name(),
                FieldsToRender.Weight.name(), FieldsToRender.Year.name(),
                FieldsToRender.Mileage.name(), FieldsToRender.Color.name(),
                FieldsToRender.Income.name(), FieldsToRender.Tax.name(),
                FieldsToRender.Profit.name());

        for (Driveable vehicle : vehicles) {
            System.out.printf(pattern,
                    vehicle.getId(), vehicle.getTypeName(), vehicle.getModel(),
                    vehicle.getRegistrationNumber(), vehicle.getWeight(), vehicle.getManufactureYear(),
                    vehicle.getMileage(), vehicle.getColor(), vehicle.getTotalIncome(), vehicle.getCalcTaxPerMonth(),
                    vehicle.getTotalProfit()
            );
        }
    }

    private static void getColumnsLengthData(Map<String, Long> columnsLengths, FieldsToRender[] fields,
                                      List<Driveable> vehicles) {
        for (FieldsToRender field : fields) {
            columnsLengths.put(field.name(), Long.valueOf(field.name().length()));
        }

        for (Driveable vehicle : vehicles) {
            columnsLengths.computeIfPresent(FieldsToRender.id.name(),
                    (key, oldValue) -> Long.max(oldValue, (vehicle.getId())));
            columnsLengths.computeIfPresent(FieldsToRender.Type.name(),
                    (key, oldValue) -> Long.max(oldValue, vehicle.getTypeName().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Model.name(),
                    (key, oldValue) -> Long.max(oldValue, vehicle.getModel().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Color.name(),
                    (key, oldValue) -> Long.max(oldValue, vehicle.getColor().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Profit.name(),
                    (key, oldValue) -> Long.max(oldValue, ((Object) vehicle.getTotalProfit()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Weight.name(),
                    (key, oldValue) -> Long.max(oldValue, ((Object) vehicle.getWeight()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Mileage.name(),
                    (key, oldValue) -> Long.max(oldValue, ((Object) vehicle.getMileage()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Number.name(),
                    (key, oldValue) -> Long.max(oldValue, vehicle.getRegistrationNumber().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Income.name(),
                    (key, oldValue) -> Long.max(oldValue, ((Object) vehicle.getTotalIncome()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Year.name(),
                    (key, oldValue) -> Long.max(oldValue, ((Object) vehicle.getManufactureYear()).toString().length()));
            columnsLengths.computeIfPresent(FieldsToRender.Tax.name(),
                    (key, oldValue) -> Long.max(oldValue, ((Object) vehicle.getCalcTaxPerMonth()).toString().length()));
        }
    }
}

