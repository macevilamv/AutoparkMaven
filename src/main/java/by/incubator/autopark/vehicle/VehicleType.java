package by.incubator.autopark.vehicle;

import java.util.Objects;

public class VehicleType implements TypeInterface {
    private Long id;
    private String typeName;
    private Double taxCoefficient;

    VehicleType () {}


    public VehicleType (Long id, String name, double taxation) {
        this.id = id;
        this.typeName = name;
        this.taxCoefficient = taxation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName (String typeName) {
        this.typeName = typeName;
    }

    public Double getTaxCoefficient() {
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
