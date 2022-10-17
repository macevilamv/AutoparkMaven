package by.incubator.autopark.vehicle;

public interface Driveable {
    Long getId();
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
}
