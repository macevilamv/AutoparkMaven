package by.incubator.autopark.facade;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.dto.RentDto;
import by.incubator.autopark.dto.TypeDto;
import by.incubator.autopark.dto.VehicleDto;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class FacadeService {
    @Autowired
    VehicleCollection entityCollection;

    public FacadeService() {
    }

    public List<TypeDto> getTypes() {
        return
                entityCollection.getTypes()
                .stream()
                .map(type -> {
                    return TypeDto.builder()
                            .id(type.getId())
                            .name(type.getTypeName())
                            .coefTaxes(type.getTaxCoefficient())
                            .build();
                }).collect(Collectors.toList());
    }

    public List<VehicleDto> getVehicles() {
        return entityCollection
                .getVehicles()
                .stream()
                .map(vehicle -> {
                    return VehicleDto.builder()
                            .id(vehicle.getId())
                            .type(vehicle.getTypeId())
                            .typeName(vehicle.getTypeName())
                            .coefTaxes(vehicle.getType().getTaxCoefficient())
                            .model(vehicle.getModel())
                            .registrationNumber(vehicle.getRegistrationNumber())
                            .weight(vehicle.getWeight())
                            .manufactureYear(vehicle.getManufactureYear())
                            .mileage(vehicle.getMileage())
                            .color(vehicle.getColor())
                            .engineName(vehicle.getEngine().getName())
                            .tankCapacity(vehicle.getEngine().getCapacity())
                            .engineVolume(vehicle.getEngine().getVolume())
                            .per100Kilometers(vehicle.getEngine().getConsumption())
                            .maxKilometers(vehicle.getEngine().getMaxKilometers())
                            .tax(vehicle.getCalcTaxPerMonth())
                            .income(vehicle.getTotalIncome())
                            .profit(vehicle.getTotalProfit())
                            .build();
                }).collect(Collectors.toList());
    }

    public List<RentDto> getRents() {
        return entityCollection
                .getRents()
                .stream()
                .map(rent -> {
                    try {
                        return RentDto.builder()
                                .id(rent.getRentId())
                                .vehicleId(rent.getVehicleId())
                                .date(new SimpleDateFormat("dd.MM.yyyy").parse(rent.getRentDate()))
                                .cost(rent.getRentCost())
                                .build();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }
}
