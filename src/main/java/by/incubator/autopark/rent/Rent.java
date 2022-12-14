package by.incubator.autopark.rent;

public class Rent implements Rentable {
    private Long RentId;
    private Long vehicleId;
    private String rentDate;
    private Double rentCost;
    private static Long rentCounter = 0L;

    public Rent() {}

    public Rent(Long vehicleId, String rentDate, Double rentCost) {
        rentCounter++;
        this.vehicleId = vehicleId;
        this.rentDate = rentDate;
        this.rentCost = rentCost;
        this.RentId = rentCounter;
    }

    @Override
    public Long getRentId() {
        return this.RentId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public Double getRentCost() {
        return rentCost;
    }

    public void setRentCost(Double rentCost) {
        this.rentCost = rentCost;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "vehicleId=" + vehicleId +
                ", rentDate='" + rentDate + '\'' +
                ", rentCost=" + rentCost +
                '}';
    }
}
