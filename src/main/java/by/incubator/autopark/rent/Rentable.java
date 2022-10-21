package by.incubator.autopark.rent;

public interface Rentable {
    Long getRentId();
    Long getVehicleId();
    Double getRentCost();

    String getRentDate();
}
