package by.incubator.autopark.vehicle;

import by.incubator.autopark.engine.CombustionEngine;
import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.exceptions.NotVehicleException;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.parsers.csv_parsers.VehicleParserFromCsvFile;
import by.incubator.autopark.rent.Rentable;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.incubator.autopark.service.TechnicalSpecialist.*;
public class Vehicle implements Comparable<Vehicle>, Driveable {
    private TypeInterface type;
    private Long id;
    private CarColor color;
    private String model;
    private String registrationNumber;
    private Double weight;
    private Integer mileage;
    private Integer manufactureYear;
    private Startable engine;
    private static int defaultCarCounter;
    private List<Rentable> rentList;
    @Autowired
    VehicleParserFromCsvFile parser;

    public Vehicle() {
        type = new VehicleType(0L, "DEFAULT", 0.0d);
        model = "DEFAULT" + defaultCarCounter++;
        registrationNumber = "DEFAULT";
        manufactureYear = 0;
        color = CarColor.WHITE;
        registrationNumber = "DEFAULT";
        weight = 0.0;
        engine = new CombustionEngine("DEFAULT", 0, 0, 0, 0);
        id = 0L;
        rentList = new ArrayList<>();
    }

    public Vehicle(Long id, TypeInterface type, CarColor color,
                   String model, String registrationNumber,
                   double mass, int mileage,
                   int manufactureYear, Startable engine, List<Rentable> rentList) {
        try {
            if (validateVehicleType(type)) {
                this.type = type;
            } else {
                throw new NotVehicleException("Incorrect vehicle type: " + type);
            }
            if (validateColor(color)) {
                this.color = color;
            } else {
                throw new NotVehicleException("Incorrect color: " + color);
            }
            if (validateModelName(model)) {
                this.model = model;
            } else {
                throw new NotVehicleException("Incorrect model: " + model);
            }
            if (validateRegistrationNumber(registrationNumber)) {
                this.registrationNumber = registrationNumber;
            } else {
                throw new NotVehicleException("Incorrect registration number: " + registrationNumber);
            }
            if (validateWeight(mass)) {
                this.weight = mass;
            } else {
                throw new NotVehicleException("Incorrect weight: " + mass);
            }
            if (validateMileage(mileage)) {
                this.mileage = mileage;
            } else {
                throw new NotVehicleException("Incorrect mileage: " + mileage);
            }
            if (validateManufactureYear(manufactureYear)) {
                this.manufactureYear = manufactureYear;
            } else {
                throw new NotVehicleException("Incorrect manufacture year: " + manufactureYear);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.engine = engine;
        this.rentList = rentList;
    }

    public Double getTotalProfit() {
        return Double.parseDouble(String.format("%.2f",getTotalIncome() - getCalcTaxPerMonth()));
    }

    public Double getCalcTaxPerMonth() {
        if (this.model.equals("DEFAULT") && this.registrationNumber.equals("DEFAULT"))
            return 0.0d;

        return Double.parseDouble(String.format("%.2f",
                (this.weight * 0.0013) + (type.getTaxCoefficient() * engine.getTaxPerMonth() * 30) + 5));
    }

    public Double getTotalIncome() {
        Double sum = 0.0d;

        for (Rentable rent : rentList) {
            sum += rent.getRentCost();
        }
        return sum;
    }

    @Override
    public String toString() {
        return  id + ", "
                + type.getTypeName() + ", "
                + model + ", "
                + color + ", "
                + registrationNumber + ", "
                + weight + ", "
                + mileage + ", "
                + manufactureYear + ", "
                + getCalcTaxPerMonth() + ", "
                + engine.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Vehicle that = (Vehicle) o;

        return this.type.equals(that.type) && this.model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, model);
    }

    @Override
    public int compareTo(Vehicle secondVehicle) {
        if (this.manufactureYear == secondVehicle.manufactureYear) {
            return Integer.compare(this.mileage, secondVehicle.mileage);
        } else if (this.manufactureYear > secondVehicle.manufactureYear) {
            return 1;
        } else {
            return -1;
        }
    }

    public Startable getEngine() {
        return engine;
    }

    public void setEngine(Startable engine) {
        this.engine = engine;
    }

    public TypeInterface getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    @SneakyThrows
    @Override
    public String getTypeName() {
        return this.type.getTypeName();
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setType(VehicleType type) {
        try {
            if (validateVehicleType(type)) {
                this.type = type;
            } else {
                throw new NotVehicleException("Incorrect vehicle type: " + type);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public String getColor() {
        return color.name();
    }

    public String getColorName() {
        return color.name();
    }

    public void setColor(CarColor color) {
        try {
            if (validateColor(color)) {
                this.color = color;
            } else {
                throw new NotVehicleException("Incorrect color: " + color);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        try {
            if (validateModelName(model)) {
                this.model = model;
            } else {
                throw new NotVehicleException("Incorrect model: " + model);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        try {
            if (validateRegistrationNumber(registrationNumber)) {
                this.registrationNumber = registrationNumber;
            } else {
                throw new NotVehicleException("Incorrect registration number: " + registrationNumber);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        try {
            if (validateWeight(weight)) {
                this.weight = weight;
            } else {
                throw new NotVehicleException("Incorrect weight: " + weight);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        try {
            if (validateMileage(mileage)) {
                this.mileage = mileage;
            } else {
                throw new NotVehicleException("Incorrect mileage: " + mileage);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        try {
            if (validateManufactureYear(manufactureYear)) {
                this.manufactureYear = manufactureYear;
            } else {
                throw new NotVehicleException("Incorrect manufacture year: " + manufactureYear);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }
}
