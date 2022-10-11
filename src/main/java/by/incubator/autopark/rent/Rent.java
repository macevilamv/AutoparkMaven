package by.incubator.autopark.rent;

public class Rent implements Rentable {
    private Long vehicleId;
    private String rentDate;
    private Double rentCost;

    public Rent() {}

    public Rent(Long vehicleId, String rentDate, Double rentCost) {
        this.vehicleId = vehicleId;
        this.rentDate = rentDate;
        this.rentCost = rentCost;
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
