package by.incubator.autopark.rent;

public class Rent {
    private int vehicleId;
    private String rentDate;
    private double rentCost;

    public Rent() {}

    public Rent(int vehicleId, String rentDate, double rentCost) {
        this.vehicleId = vehicleId;
        this.rentDate = rentDate;
        this.rentCost = rentCost;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public double getRentCost() {
        return rentCost;
    }

    public void setRentCost(double rentCost) {
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
