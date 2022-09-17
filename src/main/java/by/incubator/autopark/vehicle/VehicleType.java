package by.incubator.autopark.vehicle;

import java.util.Objects;

public class VehicleType {
    private int id;
    private String typeName;
    private double taxCoefficient;

    VehicleType () {}


    public VehicleType (int id, String name, double taxation) {
        this.id = id;
        this.typeName = name;
        this.taxCoefficient = taxation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName (String typeName) {
        this.typeName = typeName;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxCoefficient (double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }

    public void display () {
        System.out.println("TypeName= " + typeName + "\n" + "TaxCoefficient= " + taxCoefficient);
    }

    public void getString () {
        System.out.println(typeName +", " + "\""+ taxCoefficient +"\"");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleType that = (VehicleType) o;

        return Double.compare(that.taxCoefficient, taxCoefficient) == 0 && typeName.equals(that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, taxCoefficient);
    }
}
