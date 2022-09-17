package by.incubator.autopark.engine;

public abstract class AbstractEngine implements Startable {
    String engineName;
    double engineTypeTaxCoefficient;

    AbstractEngine(String engineName, double engineTypeTaxCoefficient) {
        setEngineName(engineName);
        setEngineTypeTaxCoefficient(engineTypeTaxCoefficient);
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public double getEngineTypeTaxCoefficient() {
        return engineTypeTaxCoefficient;
    }

    public void setEngineTypeTaxCoefficient(double engineTypeTaxCoefficient) {
        this.engineTypeTaxCoefficient = engineTypeTaxCoefficient;
    }

    @Override
    public String toString(){
        return getEngineName() + ", "
        + getEngineTypeTaxCoefficient();
    }
}
